package com.superphonebook.utils;

import java.util.Random;

public class RandomUtil {
    private Random ran = new Random();
    private String[] frist = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯",
	    "陈", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张",
	    "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "柏", "水",
	    "窦", "章", "云", "苏", "潘", "葛", "范", "彭", "鲁", "韦", "马", "苗", "凤",
	    "花", "方", "俞", "任", "袁", "柳", "鲍", "史", "唐", "费", "薛", "雷", "贺",
	    "倪", "汤", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅",
	    "皮", "齐", "康", "伍", "余", "元", "顾", "孟", "平", "黄", "和", "穆", "萧",
	    "尹", "姚", "邵", "汪", "毛", "狄", "米", "贝", "明", "计", "伏", "成", "戴",
	    "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜",
	    "阮", "蓝", "席", "季", "麻", "贾", "路", "娄", "江", "童", "颜", "郭", "梅",
	    "盛", "林", "钟", "徐", "邱", "高", "夏", "田", "樊", "胡", "凌", "霍", "虞",
	    "万", "柯", "管", "卢", "莫", "干", "解", "应", "丁", "龚", "程", "嵇", "邢",
	    "滑", "裴", "陆", "荣", "翁", "荀", "羊", "于", "段", "富", "巴", "谷", "侯",
	    "龙", "叶", "黎", "白", "池", "温", "容", "向", "古", "易", "戈", "廖", "步",
	    "都", "文", "欧", "巩", "聂", "敖", "关", "后", "荆", "游", "帅", "佘", "年",
	    "阳", "佟", "言", "福", "铁", "迟", "官", "冼", "司马", "上官", "欧阳", "夏侯",
	    "诸葛", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "淳于", "单于",
	    "太叔", "公孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "司徒", "南门", "呼延",
	    "端木", "拓跋", "百里", "东郭", "左丘", "东门", "西门", "南宫", "独孤", "南郭", "北宫" };
    private String[] second = { "大", "小", "二", "三", "天", "地", "玄", "黄", "八",
	    "九", "伯", "仲", "叔", "季", "东", "西", "南", "北", "中", "发", "红", "黄",
	    "绿", "若", "弱", "强", "软", "硬", "长", "短", "老", "智", "不", "伟", "安",
	    "神", "经", "病", "阿", "金", "银", "铜", "铁", "高", "矮", "胖", "瘦", "鸡",
	    "狗", "猪", "蛋", "傻", "楞", "逗", "呆", "宅", "腐", "翔", "美", "丑", "壮",
	    "是", "非", "为", "否", "木", "春", "夏", "秋", "冬", "你", "我", "他", "她",
	    "它", "十", "百", "千", "万", "" };
    private String[] third = { "鼠", "牛", "虎", "兔", "龙", "猴", "鸡", "狗", "猪",
	    "黑", "白", "明", "蛋", "高", "矮", "胖", "瘦", "美", "丑", "壮", "贫", "东",
	    "西", "南", "北", "中", "花", "鸟", "鱼", "虫", "哥", "弟", "姐", "妹", "锅",
	    "碗", "瓢", "盆", "锄", "铲", "镐", "傻", "楞", "痴", "狂", "子", "儿", "疯",
	    "癫", "呆", "笨", "树", "木", "驴", "蠢", "懒", "萌", "富", "福", "梅", "兰",
	    "竹", "菊", "神", "鬼", "巫", "侠", "乐", "叔", "姨", "姑", "丈", "剩", "叟",
	    "妪", "江", "河", "湖", "海", "" };
    private String[] phone = { "0", "9", "8", "7", "6", "5", "4", "3", "2", "1" };

    private String getRandomX(String[] ss) {
	return ss[ran.nextInt(ss.length)];
    }

    public String getRandomName() {
	return "" + getRandomX(frist) + getRandomX(second) + getRandomX(third);
    }

    public String getRandomPhone() {
	return "1" + getRandomX(phone) + getRandomX(phone) + getRandomX(phone)
		+ getRandomX(phone) + getRandomX(phone) + getRandomX(phone)
		+ getRandomX(phone) + getRandomX(phone) + getRandomX(phone)
		+ getRandomX(phone);
    }
}