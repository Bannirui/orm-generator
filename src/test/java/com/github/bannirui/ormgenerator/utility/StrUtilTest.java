package com.github.bannirui.ormgenerator.utility;

import junit.framework.TestCase;

public class StrUtilTest extends TestCase {

	public void testLowerScore2LowerCamel() {
		String s1 = "a";
		String ret1 = StrUtil.lowerScore2LowerCamel(s1);
		assertEquals(ret1, "a");

		String s2 = "_user";
		String ret2 = StrUtil.lowerScore2LowerCamel(s2);
		assertEquals(ret2, "_user");

		String s3 = "user_";
		String ret3 = StrUtil.lowerScore2LowerCamel(s3);
		assertEquals(ret3, "user_");

		String s4 = "tb_user";
		String ret4= StrUtil.lowerScore2LowerCamel(s4);
		assertEquals(ret4, "tbUser");
	}

	public void testLowerScore2UpperCamel() {
		String s1 = "a";
		String ret1 = StrUtil.lowerScore2UpperCamel(s1);
		assertEquals(ret1, "A");

		String s2 = "_user";
		String ret2 = StrUtil.lowerScore2UpperCamel(s2);
		assertEquals(ret2, "_user");

		String s3 = "user_";
		String ret3 = StrUtil.lowerScore2UpperCamel(s3);
		assertEquals(ret3, "User_");

		String s4 = "tb_user";
		String ret4= StrUtil.lowerScore2UpperCamel(s4);
		assertEquals(ret4, "TbUser");
	}
}