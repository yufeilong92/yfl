package com.backpacker.yflLibrary.java;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @Author : YFL  is Creating a porject in del
 * @Package : com.backpacker.yflLibrary.java.sys
 * @Email : yufeilong92@163.com
 * @Time :2020/8/13 14:52
 * @Purpose :校验器：利用正则表达式校验邮箱、手机号等
 */
public class JavaValidator {
	/**
	 * 正则表达式：验证用户名
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

	/**
	 * 正则表达式：验证手机号  .matches("^[1][3578]\\d{9}")
	 */
//	public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";//
	public static final String REGEX_MOBILE = "^(13[0-9]|15[012356789]|17[03678]|18[0-9]|14[57])[0-9]{8}$";
	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

	/**
	 * 正则表达式：验证身份证
	 */
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

	/**
	 * 正则表达式：验证URL
	 */
	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

	/**
	 * 校验用户名
	 * 
	 * @param username
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUsername(String username) {
		return Pattern.matches(REGEX_USERNAME, username);
	}

	/**
	 * 校验密码
	 * 
	 * @param password
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isPassword(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}

	/**
	 * 校验手机号
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 校验汉字
	 * 
	 * @param chinese
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}

	/**
	 * 校验身份证
	 * 
	 * @param idCard
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}

	/**
	 * 校验URL
	 *
	 * @param url
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUrl(String url) {
		return Pattern.matches(REGEX_URL, url);
	}

	/**
	 * 校验IP地址
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isIPAddr(String ipAddr) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}

	public static void main(String[] args) {
		String username = "fdsdfsdj";
		System.out.println(JavaValidator.isUsername(username));
		System.out.println(JavaValidator.isChinese(username));
	}
	public static boolean check(String str, String regex) {
		if (str == null) {
			return false;
		}
		return str.matches(regex);
	}

	public static boolean isEmailStr(String email) {
		String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		return check(email, regex);
	}

	public static boolean isMobileStr(String cellphone) {
		// String regex =
		// "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9])|177)\\d{8}$";
		String regex = "^1\\d{10}$";
		return check(cellphone, regex);
	}

	public static boolean isTelphone(String telephone) {
		String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		return check(telephone, regex);
	}

	// 校验银行卡
	public static boolean checkCreditCard(String number) {
		if (TextUtils.isEmpty(number)) {
			return false;
		}
		number = number.replaceAll(" ", "");

		int s1 = 0, s2 = 0;
		String reverse = new StringBuffer(number).reverse().toString();
		for (int i = 0; i < reverse.length(); i++) {
			int digit = Character.digit(reverse.charAt(i), 10);
			if (i % 2 == 0) {// this is for odd digits, they are 1-indexed in
				// the algorithm
				s1 += digit;
			} else {// add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
				s2 += 2 * digit;
				if (digit >= 5) {
					s2 -= 9;
				}
			}
		}
		return (s1 + s2) % 10 == 0;
	}

	/**
	 * 隐藏手机号
	 */
	public static String hideMobile(String mobile) {
		String hideMobile = mobile.substring(0, 3) + "****"
				+ mobile.substring(7, mobile.length());
		return hideMobile;
	}

	// 校验身份证
	public static boolean valideIdCard(String idCard) {

		String idCardPattern = "^\\d{17}(\\d|X|x)$"; // 前17位为数字，最后一位为数字或X
		// 验证格式
		if (idCard == null)
			return false;
		if (!idCard.matches(idCardPattern)) {
			return false;
		}
		idCard = idCard.toUpperCase();
		/*
		 * 11 北京市 12 天津市 13 河北省 14 山西省 15 内蒙古自治区 21 辽宁省 22 吉林省 23 黑龙江省 31 上海市 32
		 * 江苏省 33 浙江省 34 安徽省 35 福建省 36 江西省 37 山东省 41 河南省 42 湖北省 43 湖南省 44 广东省 45
		 * 广西壮族自治区 46 海南省 50 重庆市 51 四川省 52 贵州省 53 云南省 54 西藏自治区 61 陕西省 62 甘肃省 63
		 * 青海省 64 宁夏回族自治区 65 新疆维吾尔自治区 71 台湾省 81 香港特别行政区 82 澳门特别行政区
		 */
		String provinces = "11, 12, 13, 14, 15, 21, 22, 23, 31, 32, 33, 34, 35, 36, 37, 41, 42, 43, 44, 45, 46, 50, 51, 52, 53, 54, 61, 62, 63, 64, 65, 71, 81, 82";

		// 验证省级代码
		if (!provinces.contains(idCard.substring(0, 2))) {
			return false;
		}
		// 验证年月日
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			Date birthday = df.parse(idCard.substring(6, 14));
			Date min = df.parse("19000101");
			Date max = df.parse(df.format(new Date()));
			if (birthday.before(min) || birthday.after(max)) {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}

		// 验证校验位
		/*
		 * 关于身份证号码最后一位的校验码的算法如下： 　 ∑(a[i]*W[i]) mod 11 ( i = 2, 3, ..., 18 ) 　
		 * 　"*" ： 表示乘号 　　i： 表示身份证号码每一位的序号，从右至左，最左侧为18，最右侧为1。 　　a[i]： 表示身份证号码第 i
		 * 位上的号码 　　W[i]： 表示第 i 位上的权值 W[i] = 2^(i-1) mod 11 　　设：R = ∑(a[i]*W[i])
		 * mod 11 ( i = 2, 3, ..., 18 ) 　　C = 身份证号码的校验码 　　则R和C之间的对应关系如下表： 　　　R：0
		 * 1 2 3 4 5 6 7 8 9 10 　　　C：1 0 X 9 8 7 6 5 4 3 2 　　由此看出 X 就是 10，罗马数字中的
		 * 10 就是X，所以在新标准的身份证号码中可能含有非数字的字母X。
		 */
		char residues[] = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3',
				'2' };
		long sum = 0;
		for (int i = 0; i < 17; i++) {
			sum += Integer.valueOf(idCard.substring(i, i + 1))
					* (Math.pow(2, (18 - 1 - i)) % 11);
		}
		return idCard.charAt(17) == residues[(int) (sum % 11)];

	}

	private static InputFilter emojiFilter = new InputFilter() {
		Pattern emoji = Pattern.compile(
				"[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

		@Override
		public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				return "";
			}
			return null;
		}
	};
	/**
	 * @apiNote 禁止输入表情，Edt加上过滤器
	 */
	public static InputFilter[] emojiFilters = {emojiFilter};

//--check校验类-------------------------------------------------------------------------------------------------------

	/**
	 * @param urls 判断字符串是否为URL（https://blog.csdn.net/bronna/article/details/77529145）
	 * @return true:是URL、false:不是URL
	 */
	public static boolean checkUrl(String urls) {
		boolean isUrl;
		// 判断是否是网址的正则表达式
		String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
				+ "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";

		Pattern pat = Pattern.compile(regex.trim());
		Matcher mat = pat.matcher(urls.trim());
		isUrl = mat.matches();
		return isUrl;
	}

	/**
	 * @apiNote true→不包含符号
	 */
	public static boolean checkSymbol(String str) {
		return str.matches("^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$");
	}

	/**
	 * @apiNote true→纯汉字
	 */
	public static boolean checkChineseCharacter(String str) {
		return str.matches("^[\\u4e00-\\u9fa5]+$");
	}

	/**
	 * @apiNote true→ (汉字)||(字母)
	 */
	public static boolean checkChineseLetter(String str) {
		return str.matches("^[a-zA-Z\\u4e00-\\u9fa5]+$");
	}

	/**
	 * @apiNote true→(字母)||(数字)
	 */
	public static boolean checkLetterDigit(String str) {
		return str.matches("^[A-Za-z0-9]+$");
	}

	/**
	 * @apiNote true→(汉字)||(字母)||(数字)
	 */
	public static boolean checkChineseLetterDigit(String str) {
		return str.matches("^[a-z0-9A-Z\\u4e00-\\u9fa5]+$");
	}

	/**
	 * @apiNote true→昵称可由：(中文)||(英文)||(数字)||("_")||("-")
	 */
	public static boolean checkNickName(String nickName) {
		return nickName.matches("^[_\\-a-zA-Z0-9\\u4e00-\\u9fa5]+$");
	}

	/**
	 * @param digit 一位或多位0-9之间的整数
	 * @apiNote true→(正整数)||(负整数)
	 */
	public static boolean checkDigit(String digit) {
		return digit.matches("-?[1-9]\\d+");
	}


	/**
	 * @param date 日期，格式：1992-09-03，或1992.09.03
	 * @apiNote true→是上述年月日
	 */
	public static boolean checkDate(String date) {
		String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
		return Pattern.matches(regex, date);
	}

//--check判断类-------------------------------------------------------------------------------------------------------

	/**
	 * @apiNote true→是手机号码
	 */
	public static boolean iskPhoneNum(String phone) {
		return phone.matches("(\\+\\d+)?1\\d{10}$");
	}

	/**
	 * @apiNote true→是身份证号码
	 */
	public static boolean isIdCard(String idcard) {
		return idcard.matches("[1-9]\\d{16}[a-zA-Z0-9]");
	}



//--Fun工具类-------------------------------------------------------------------------------------------------------

	/**
	 * @apiNote 隐藏手机号中间4位
	 * @eg 185****9095
	 */
	public static String funHidePhone(String input) {
		return input.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	/**
	 * @apiNote 隐藏身份证号中间N位
	 * @eg 511***********5815
	 */
	public static String funHideIDCard(String input) {
		return input.replaceAll("(\\d{3})\\d+(\\d{4})", "$1***********$2");
	}

	/**
	 * @apiNote 隐藏银行卡号前几位
	 * @eg **** **** **** **** 309
	 */
	public static String funHideBankF(String input) {
		return input.replaceAll("([\\d]{4})(?=\\d)", "**** ");
	}

	/**
	 * @apiNote 银行卡号每隔四位增加一个空格
	 * @eg 6225 8801 3770 6868
	 */
	public static String funBankCardChar(String input) {
		return input.replaceAll("([\\d]{4})(?=\\d)", "$1 ");
	}

	/**
	 * @param input 6225880137706868
	 * @param text  "-"
	 * @apiNote 银行卡号每隔四位增加一个指定字符
	 * @apiNote 银行卡号每隔四位增加一个指定字符
	 * @eg 6225-8801-3770-6868
	 */
	public static String funBankCardChar(String input, String text) {
		return input.replaceAll("([\\d]{4})(?=\\d)", "$1" + text);
	}

	/**
	 * @param digit 1236.51634
	 * @apiNote 每隔3位加一个逗号
	 * 方式一:使用DecimalFormat
	 * @eg 1, 236.516
	 */
	public static String funformatDigit_3(double digit) {
		DecimalFormat df1 = (DecimalFormat) DecimalFormat.getInstance();
		df1.setGroupingSize(3);
		return df1.format(digit);
	}

	/**
	 * @param input 1236.51634
	 * @apiNote 每隔3位加一个逗号
	 * 方式二:使用正则表达式
	 * @eg 1, 236.5, 1634
	 */
	public static String funformatDigit_3(String input) {
		String regx = "(?<=\\d)(\\d{3})";
		return input.replaceAll(regx, ",$1");
	}

	/**
	 * @param digit  5556.7468f
	 * @param fotmat "#,##0.00"
	 * @return 5, 556.75
	 * @apiNote 按照指定格式转化数字
	 */
	public static String formatDigitString(double digit, String fotmat) {
		return new DecimalFormat(fotmat).format(digit);
	}
}
