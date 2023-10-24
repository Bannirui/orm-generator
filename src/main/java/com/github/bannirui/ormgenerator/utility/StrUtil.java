package com.github.bannirui.ormgenerator.utility;

import org.apache.commons.lang3.StringUtils;

public class StrUtil {

	/**
	 * tb_user->tbUser
	 */
	public static String lowerScore2LowerCamel(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		int len = s.length();
		int idx = -1;
		// base case
		// _user->_user
		// user_->user_
		if ((idx = s.indexOf("_")) <= 0 || idx == len - 1) {
			return s;
		}
		s = s.replaceFirst("_", "");
		// [start...end)
		s = s.substring(0, idx) + Character.toUpperCase(s.charAt(idx)) + s.substring(idx + 1);
		return lowerScore2LowerCamel(s);
	}

	/**
	 * tb_user->TbUser
	 */
	public static String lowerScore2UpperCamel(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		String ret = lowerScore2LowerCamel(s);
		return Character.toUpperCase(ret.charAt(0)) + ret.substring(1);
	}
}
