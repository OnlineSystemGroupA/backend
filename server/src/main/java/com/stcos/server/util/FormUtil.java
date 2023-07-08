package com.stcos.server.util;

import com.stcos.server.entity.form.Form;
import com.stcos.server.service.FormService;
import lombok.experimental.UtilityClass;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.flowable.task.service.delegate.DelegateTask;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static final List<String> FORM_LIST =
            List.of("ApplicationForm",
                    "ApplicationVerifyForm",
                    "ContractForm",
                    "DocumentReviewForm",
                    "QuotationForm",
                    "ReportVerifyForm",
                    "TestFunctionForm",
                    "TestPlanForm",
                    "TestPlanVerifyForm",
                    "TestProblemForm",
                    "TestRecordsForm",
                    "TestReportForm",
                    "TestWorkCheckForm");

    public void addReadPermission(String userId, DelegateTask task, FormService formService) {
        for (String formName : FORM_LIST) {
            Long formMetadataId = (Long) task.getVariable(formName);
            formService.addReadPermission(formMetadataId, userId);
        }
    }

    private final String TEMPLATE_PATH = "./files/form/template/";

    public void replaceSpecialText(Form form,String fileName){

        try {
            //读文件
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(TEMPLATE_PATH + form.templateFormFile()));

            //替换内容
            Map<String, String> map = form.toTemplateFormat();
            WordAndPdfUtil.replaceWord(document,map);

            //返回流
            FileOutputStream outStream = new FileOutputStream(fileName);
            document.write(outStream);
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //数字转换为大写汉字
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
                year = MonthAbbreviationConverter.convertAbbreviationToNumber(matcher.group(1));
                month = matcher.group(2);
                day = matcher.group(3);
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
