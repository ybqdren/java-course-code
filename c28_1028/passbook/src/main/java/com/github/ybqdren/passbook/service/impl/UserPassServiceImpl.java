package com.github.ybqdren.passbook.service.impl;

import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.dao.MerchantsDao;
import com.github.ybqdren.passbook.entity.Merchants;
import com.github.ybqdren.passbook.service.IUserPassService;
import com.github.ybqdren.passbook.vo.Pass;
import com.github.ybqdren.passbook.vo.PassTemplate;
import com.github.ybqdren.passbook.vo.Response;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 用户优惠卷相关功能实现 </h1>
 **/

@Slf4j
@Service
public class UserPassServiceImpl implements IUserPassService {
    /**
     * HBase 客户端
     */
    private final HbaseTemplate hbaseTemplate;

    /** Merchants Dao */
    private final MerchantsDao merchantsDao;

    public UserPassServiceImpl(HbaseTemplate hbaseTemplate, MerchantsDao merchantsDao) {
        this.hbaseTemplate = hbaseTemplate;
        this.merchantsDao = merchantsDao;
    }

    /** 优惠卷的状态信息 */


    @Override
    public Response getUserPassInfo(Long userId) throws Exception {
        return null;
    }

    @Override
    public Response getUserUsedPassInfo(Long userId) throws Exception {
        return null;
    }

    @Override
    public Response getUserAllPassInfo(Long userId) throws Exception {
        return null;
    }

    @Override
    public Response getUserPass(Pass pass) throws Exception {
        return null;
    }

    /**
     * <h2> 通过获取的 Passes 对象构建 Map </h2>
     *
     * @param passes {@link Pass}
     * @return Map {@link  PassTemplate}
     * @throws Exception
     */
    private Map<String, PassTemplate> buildPassTemplateMap(List<Pass> passes) throws Exception {

//        String[] patterns = new String[]{"yyyy-MM-dd"};
        List<String> patterns = new ArrayList<>();
        patterns.add("yyyy-MM-dd");

        // 列族 B
        byte[] FAMILY_B = Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B);
        byte[] ID = Bytes.toBytes(Constants.PassTemplateTable.ID);
        byte[] TITLE = Bytes.toBytes(Constants.PassTemplateTable.TITLE);
        byte[] SUMMARY = Bytes.toBytes(Constants.PassTemplateTable.SUMMARY);
        byte[] DESC = Bytes.toBytes(Constants.PassTemplateTable.DESC);
        byte[] HAS_TOKEN = Bytes.toBytes(Constants.PassTemplateTable.HAS_TOKEN);
        byte[] BACKGROUND = Bytes.toBytes(Constants.PassTemplateTable.BACKGROUND);

        // 列族 C
        byte[] FAMILY_C = Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C);
        byte[] LIMIT = Bytes.toBytes(Constants.PassTemplateTable.LIMIT);
        byte[] START = Bytes.toBytes(Constants.PassTemplateTable.START);
        byte[] END = Bytes.toBytes(Constants.PassTemplateTable.END);

        List<String> templateIds = passes.stream().map(
                Pass::getTemplateId
        ).collect(Collectors.toList());

        List<Get> templateGets = new ArrayList<>(templateIds.size());
        templateIds.forEach(t -> templateGets.add(new Get(Bytes.toBytes(t))));

        // 从 hbase 中获取结果集
        Result[] templateResult = hbaseTemplate.getConnection()
                .getTable(TableName.valueOf(Constants.PassTemplateTable.TABLE_NAME))
                .get(templateGets);

        // 构造 PassTemplated -> PassTemplate Object 的 Map，用于构造 PassInfo
        Map<String, PassTemplate> templateId2Object = new HashMap<>();
        for (Result item : templateResult) {
            PassTemplate passTemplate = new PassTemplate();

            passTemplate.setId(Bytes.toInt(item.getValue(FAMILY_B, ID)));
            passTemplate.setTitle(Bytes.toString(item.getValue(FAMILY_B, TITLE)));
            passTemplate.setSummary(Bytes.toString(item.getValue(FAMILY_B, SUMMARY)));
            passTemplate.setDesc(Bytes.toString(item.getValue(FAMILY_B, DESC)));
            passTemplate.setHasToken(Bytes.toBoolean(item.getValue(FAMILY_B, HAS_TOKEN)));
            passTemplate.setBackground(Bytes.toInt(item.getValue(FAMILY_B, BACKGROUND)));

            passTemplate.setLimit(Bytes.toLong(item.getValue(FAMILY_C, LIMIT)));
            passTemplate.setStart(
                    DateUtil.parseDate(Bytes.toString(item.getValue(FAMILY_C, START)), patterns)
            );

            passTemplate.setEnd(
                    DateUtil.parseDate(Bytes.toString(item.getValue(FAMILY_C, END)), patterns)
            );

            templateId2Object.put(Bytes.toString(item.getRow()), passTemplate);
        }

        return templateId2Object;
    }

    /**
     * <h2> 通过获取的 PassTemplate 对象构造 Merchants Map </h2>
     * @param passTemplates {@link PassTemplate}
     * @return {@link Merchants}
     * @throws Exception
     */
    private Map<Integer, Merchants> buildMerchantsMap(List<PassTemplate> passTemplates) throws Exception {

        Map<Integer , Merchants> merchantsMap = new HashMap<>();

        // 通过 passTemplate 获取所有的 id
        List<Integer> merchantsIds = passTemplates.stream().map(
                PassTemplate :: getId
        ).collect(Collectors.toList());

        // 根据 id 获取所有的 merchants
        List<Merchants> merchants = merchantsDao.findByIdIn(merchantsIds);

        // 将所有的 商户信息根据 id 填充到 merchant 中
        merchants.forEach(m -> merchantsMap.put(m.getId() , m));

        return merchantsMap;
    }
}