package org.github.ybqdren.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/
public class MyClassVistor extends ClassVisitor {

    /**
     * api：版本 --> Opcodes.ASM7
     * @param classVisitor
     */
    public MyClassVistor(ClassVisitor classVisitor) {
        super(Opcodes.ASM7, classVisitor);
    }

    /**
     * 可看作开始方法
     * @param version  版本
     * @param access  访问权限
     * @param name 类名
     * @param signature 签名
     * @param superName 父类
     * @param interfaces 接口
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    /**
     * 对开始方法的修改
     * @param access
     * @param name
     * @param descriptor
     * @param signature
     * @param exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access,name,descriptor,signature,exceptions);

        if(!"<init>".equals(name) && mv!=null){
            // 为这样的方法增加记录方法执行时间的功能 ？

        }

        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }


    class MyMethodVisitor extends MethodVisitor{

        public MyMethodVisitor(MethodVisitor methodVisitor) {
            super(Opcodes.ASM7, methodVisitor);
        }

        /**
         * 相当于开始
         */
        @Override
        public void visitCode() {
            super.visitCode();

            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
        }

        /**
         * 相当于结束
         * @param opcode
         */
        @Override
        public void visitInsn(int opcode) {
            super.visitInsn(opcode);

            // 操作码一定要大于等于IRETURN 并且也不能达到RETURN
            if(opcode >= Opcodes.IRETURN &&
                opcode <= Opcodes.RETURN ||
                opcode == Opcodes.ATHROW){    // 是否抛出异常

                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                    mv.visitVarInsn(Opcodes.LSTORE, 3);
                    Label l7 = new Label();
                    mv.visitLabel(l7);
                    mv.visitLineNumber(20, l7);
                    mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
                    mv.visitInsn(Opcodes.DUP);
                    mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
                    mv.visitLdcInsn("invoke method total time ====");
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                    mv.visitVarInsn(Opcodes.LLOAD, 3);
                    mv.visitVarInsn(Opcodes.LLOAD, 1);
                    mv.visitInsn(Opcodes.LSUB);
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                    Label l8 = new Label();
                    mv.visitLabel(l8);
                    mv.visitLineNumber(21, l8);
            }
        }
    }
}
