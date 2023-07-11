package com.stcos.server.util;

import com.stcos.server.entity.form.Form;
import com.stcos.server.entity.form.FormType;
import lombok.experimental.UtilityClass;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/7/5 16:58
 */

@UtilityClass
public class FormUtil {

    /**
     * 表单文件名到模板文件路径的映射
     */
    private static final Map<String, String> FORM_TEMPLATE_FILE_MAP = new HashMap<>() {{
        put(FormType.TYPE_APPLICATION_FORM, "/template/NST－04－JS002－2018－软件项目委托测试申请表.docx");
        put(FormType.TYPE_APPLICATION_VERIFY_FORM, "/template/NST－04－JS002－2018－软件项目委托测试申请表（审核信息部分）.docx");
        put(FormType.TYPE_CONTRACT_FORM, "/template/NST－04－JS004－2018－软件委托测试合同.docx");
        put(FormType.TYPE_CONFIDENTIALITY_FORM, "/template/NST－04－JS005－2018－软件项目委托测试保密协议.doc");
        put(FormType.TYPE_DOCUMENT_REVIEW_FORM, "/template/NST－04－JS014－2018 - 软件文档评审表.docx");
        put(FormType.TYPE_QUOTATION_FORM, "/template/1报价单.docx");
        put(FormType.TYPE_REPORT_VERIFY_FORM, "/template/NST－04－JS010－2018－测试报告检查表.doc");
        put(FormType.TYPE_TEST_FUNCTION_FORM, "/template/NST－04－JS003－2018－委托测试软件功能列表.doc");
        put(FormType.TYPE_TEST_PLAN_FORM, "/template/NST－04－JS006－2018－软件测试方案.docx");
        put(FormType.TYPE_TEST_PLAN_VERIFY_FORM, "/template/NST－04－JS013－2018 - 测试方案评审表.doc");
//       put(FormType.TYPE_TEST_RECORDS_FORM, "/template/NST－04－JS009－2018－软件测试记录（电子记录）.xlsx");
        put(FormType.TYPE_TEST_REPORT_FORM, "/template/NST－04－JS007－2018－软件测试报告.docx");
        put(FormType.TYPE_TEST_WORK_CHECK_FORM, "/template/NST－04－JS012－2018－软件项目委托测试工作检查表.doc");
//       put(FormType.TYPE_TEST_PROBLEM_FORM, "/template/NST－04－JS011－2018－软件测试问题清单（电子记录）.xlsx");
    }};


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static File replaceSpecialText(Form form, String formName, String fileName) {
        try {
            URI uri = Objects.requireNonNull(FormUtil.class.getResource(FORM_TEMPLATE_FILE_MAP.get(formName))).toURI();
            //读文件
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(uri.getPath()));
            //替换内容
            Map<String, String> map = form.toTemplateFormat();
            WordAndPdfUtil.replaceWord(document, map);

            File file = new File(fileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            //返回流
            FileOutputStream outStream = new FileOutputStream(fileName);
            document.write(outStream);
            outStream.close();
            /// !!!!!!!!!!!!!!!!!!!
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(fileName);
    }

    /**
     * 表单名到表单原始中文文件名的映射
     */
    private static final Map<String, String> FORM_CHINESE_NAME_MAP = new HashMap<>() {{
        put(FormType.TYPE_APPLICATION_FORM, "NST－04－JS002－2018－软件项目委托测试申请表");
        put(FormType.TYPE_APPLICATION_VERIFY_FORM, "NST－04－JS002－2018－软件项目委托测试申请表（审核信息部分）");
        put(FormType.TYPE_CONTRACT_FORM, "NST－04－JS004－2018－软件委托测试合同");
        put(FormType.TYPE_CONFIDENTIALITY_FORM, "NST－04－JS005－2018－软件项目委托测试保密协议");
        put(FormType.TYPE_DOCUMENT_REVIEW_FORM, "NST－04－JS014－2018 - 软件文档评审表");
        put(FormType.TYPE_QUOTATION_FORM, "1报价单");
        put(FormType.TYPE_REPORT_VERIFY_FORM, "NST－04－JS010－2018－测试报告检查表");
        put(FormType.TYPE_TEST_FUNCTION_FORM, "NST－04－JS003－2018－委托测试软件功能列表");
        put(FormType.TYPE_TEST_PLAN_FORM, "NST－04－JS006－2018－软件测试方案");
        put(FormType.TYPE_TEST_PLAN_VERIFY_FORM, "NST－04－JS013－2018 - 测试方案评审表");
        put(FormType.TYPE_TEST_RECORDS_FORM, "NST－04－JS009－2018－软件测试记录（电子记录）");
        put(FormType.TYPE_TEST_REPORT_FORM, "NST－04－JS007－2018－软件测试报告");
        put(FormType.TYPE_TEST_WORK_CHECK_FORM, "NST－04－JS012－2018－软件项目委托测试工作检查表");
        put(FormType.TYPE_TEST_PROBLEM_FORM, "NST－04－JS011－2018－软件测试问题清单（电子记录）");
    }};


    /**
     * 获取表单中文文件名
     *
     * @param formType 表单类型
     * @return 表单原始中文文件名
     */
    public static String formName2Chinese(String formType) {
        return FORM_CHINESE_NAME_MAP.get(formType);
    }

    private static final Map<String, Boolean> FORM_CLIENT_READABLE_MAP = new HashMap<>() {{
        put(FormType.TYPE_APPLICATION_FORM, true);
        put(FormType.TYPE_APPLICATION_VERIFY_FORM, true);
        put(FormType.TYPE_CONTRACT_FORM, true);
        put(FormType.TYPE_CONFIDENTIALITY_FORM, true);
        put(FormType.TYPE_DOCUMENT_REVIEW_FORM, true);
        put(FormType.TYPE_QUOTATION_FORM, true);
        put(FormType.TYPE_REPORT_VERIFY_FORM, true);
        put(FormType.TYPE_TEST_FUNCTION_FORM, true);
        put(FormType.TYPE_TEST_PLAN_FORM, false);
        put(FormType.TYPE_TEST_PLAN_VERIFY_FORM, false);
        put(FormType.TYPE_TEST_RECORDS_FORM, true);
        put(FormType.TYPE_TEST_REPORT_FORM, true);
        put(FormType.TYPE_TEST_WORK_CHECK_FORM, false);
        put(FormType.TYPE_TEST_PROBLEM_FORM, true);
    }};

    /**
     * 根据表单类型判断该表单是否对客户可见
     */
    public static boolean isClientReadable(String formType) {
        return FORM_CLIENT_READABLE_MAP.get(formType);
    }

    //数字转换为大写汉字
    @SuppressWarnings("DuplicatedCode")
    public static class NumberToChinese {
        private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        private static final String[] CN_UPPER_UNIT = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};
        private static final String CN_FULL = "整";
        private static final String CN_NEGATIVE = "负";
        private static final String CN_ZERO = "零";
        private static final int MONEY_PRECISION = 2;
        private static final String CN_DOLLAR = "元";
        private static final String CN_TEN_CENT = "角";
        private static final String CN_CENT = "分";
        private static final String CN_INTEGER = "整";

        /**
         * 将浮点数转化为大写汉字字符串
         *
         * @param num 浮点数
         * @return 大写汉字字符串
         */
        public static String convertToChinese(double num) {
            if (num == 0) {
                return CN_ZERO;
            }

            long temp = Math.round(num * 100);
            int numFen = (int) (temp % 10);
            int numJiao = (int) ((temp % 100) / 10);
            int numYuan = (int) (temp / 100);
            boolean isZeroFen = (numFen == 0);
            boolean isZeroJiao = (numJiao == 0);
            boolean isZeroYuan = (numYuan == 0);

            StringBuilder builder = new StringBuilder();
            if (numYuan > 0) {
                builder.append(convertSection(numYuan)).append(CN_DOLLAR);
            }

            if (isZeroFen && isZeroJiao) {
                builder.append(CN_FULL);
            } else if (isZeroFen) {
                builder.append(convertSection(numJiao)).append(CN_TEN_CENT);
            } else {
                if (isZeroJiao) {
                    builder.append(CN_UPPER_NUMBER[numFen]).append(CN_CENT);
                } else {
                    builder.append(convertSection(numJiao)).append(CN_TEN_CENT).append(convertSection(numFen)).append(CN_CENT);
                }
            }

            return builder.toString();
        }

        // 转化整数部分
        private static String convertSection(long section) {
            StringBuilder builder = new StringBuilder();
            boolean zeroFlag = true;
            int unitPos = 0;

            while (section > 0) {
                int v = (int) (section % 10);
                if (v == 0) {
                    if (!zeroFlag) {
                        zeroFlag = true;
                        builder.insert(0, CN_UPPER_NUMBER[v]);
                    }
                } else {
                    zeroFlag = false;
                    builder.insert(0, CN_UPPER_NUMBER[v] + CN_UPPER_UNIT[unitPos]);
                }
                unitPos++;
                section /= 10;
            }

            return builder.toString();
        }
    }

    //日期转换
    @SuppressWarnings("DuplicatedCode")
    public static class DateTransformer {
        static String regex = "\\w+\\s+(\\w+)\\s+(\\d{1,2})\\s+(\\d{4})\\s+\\d{2}:\\d{2}:\\d{2}\\s+\\w+\\+\\d{4}\\s+\\([^\\)]+\\)";

        public static String dateTransformer(String date) {
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regex);

            // 创建Matcher对象
            Matcher matcher = pattern.matcher(date);

            String month = "", day = "", year = "";

            // 查找匹配项
            if (matcher.find()) {
                month = MonthAbbreviationConverter.convertAbbreviationToNumber(matcher.group(1));
                day = matcher.group(2);
                year = matcher.group(3);
            }
            // 返回提取的日期部分
            return year + " 年 " + month + " 月 " + day + " 日 ";
        }
    }

    //月份转换为数字
    private static class MonthAbbreviationConverter {
        private static final Map<String, String> monthAbbreviations = new HashMap<>();

        static {
            monthAbbreviations.put("Jan", "1");
            monthAbbreviations.put("Feb", "2");
            monthAbbreviations.put("Mar", "3");
            monthAbbreviations.put("Apr", "4");
            monthAbbreviations.put("May", "5");
            monthAbbreviations.put("Jun", "6");
            monthAbbreviations.put("Jul", "7");
            monthAbbreviations.put("Aug", "8");
            monthAbbreviations.put("Sep", "9");
            monthAbbreviations.put("Oct", "10");
            monthAbbreviations.put("Nov", "11");
            monthAbbreviations.put("Dec", "12");
        }

        public static String convertAbbreviationToNumber(String abbreviation) {
            String monthNumber = monthAbbreviations.get(abbreviation);
            if (monthNumber != null) {
                return monthNumber;
            }
            throw new IllegalArgumentException("Invalid month abbreviation");
        }
    }
}
