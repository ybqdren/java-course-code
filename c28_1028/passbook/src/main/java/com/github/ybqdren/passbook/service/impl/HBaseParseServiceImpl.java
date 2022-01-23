package com.github.ybqdren.passbook.service.impl;

import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.service.IHBasePassService;
import com.github.ybqdren.passbook.utils.RowKeyGenUtil;
import com.github.ybqdren.passbook.vo.PassTemplate;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> Pass HBase 服务 </h1>
 **/

@Slf4j
@Service
public class HBaseParseServiceImpl implements IHBasePassService {

    /** HBase 客户端 */
    private final HbaseTemplate hbaseTemplate;

    @Autowired
    public HBaseParseServiceImpl(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Override
    public boolean dropPassTemplateToHBase(PassTemplate passTemplate) {

        if(null == passTemplate){
            return false;
        }

        String rowKey = RowKeyGenUtil.genPassTemplateRowKey(passTemplate);

        try{
            /**
             * 校验 hbase 表中是否已经存在这样的记录，如果存在直接返回 false
             *
             * getConnection  获取 hbase 连接
             * getTable 根据表名获取表
             * exists 根据 rowkey 来判断记录是否存在
             */
           if(hbaseTemplate
                   .getConnection().getTable(TableName.valueOf(Constants.PassTemplateTable.TABLE_NAME))
                   .exists(new Get(Bytes.toBytes(rowKey)))){
               log.warn("RowKey {} is already exist !" , rowKey);
               return false;
           }
        } catch (IOException e) {
           log.error("DropPassTemplateToHBase Error : {}",e.getMessage());
        }


        // 写入 hbase
        Put put = new Put(Bytes.toBytes(rowKey));

        // 填充第一列 列族 B
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.ID),
                Bytes.toBytes(passTemplate.getId())
        );

        // 填充第二列 列族 B
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.TITLE),
                Bytes.toBytes(passTemplate.getTitle())
        );

        // 填充第三列 列族 B
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.SUMMARY),
                Bytes.toBytes(passTemplate.getSummary())
        );

        // 填充第四列 列族 B
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.DESC),
                Bytes.toBytes(passTemplate.getDesc())
        );

        // 填充第五列 列族 B
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.HAS_TOKEN),
                Bytes.toBytes(passTemplate.getHasToken())
        );

        // 填充第六列 列族 B
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.BACKGROUND),
                Bytes.toBytes(passTemplate.getBackground())
        );


        // 填充第一列 列族 C
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                Bytes.toBytes(Constants.PassTemplateTable.LIMIT),
                Bytes.toBytes(passTemplate.getLimit())
        );

        // 填充第二列 列族 C
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.START),
                Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(passTemplate.getStart()))
        );

        // 填充第三列 列族 C
        put.addColumn(
                Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B),
                Bytes.toBytes(Constants.PassTemplateTable.END),
                Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(passTemplate.getEnd()))
        );

        // 将 put 进行更新，如果 rowkey 已经存在就是 update ，如果 rowkey 不存在就是 save
        hbaseTemplate.saveOrUpdate(Constants.PassTemplateTable.TABLE_NAME ,  put);

        return true;
    }
}
