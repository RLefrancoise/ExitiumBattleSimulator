package model;

public class Config {
	private Config() {}
	
	//player
	public static int DEFAULT_PV = 50;
	public static int DEFAULT_PF = 50;
	public static int DEFAULT_ATK = 5;
	public static int DEFAULT_DEF = 5;
	public static int DEFAULT_RES = 5;
	public static int DEFAULT_SPD = 5;
	public static int DEFAULT_FLUX = 5;
	
	public static int PV_PER_DEF_POINT = 5;
	public static int PF_PER_RES_POINT = 5;
	public static int ATK_PER_POINT = 1;
	public static int DEF_PER_POINT = 1;
	public static int RES_PER_POINT = 1;
	public static int SPD_PER_POINT = 1;
	public static int FLUX_PER_POINT = 1;
	
	public static float ORB_LVL1_MULTIPLIER = 0.05f;
	public static float ORB_LVL2_MULTIPLIER = 0.1f;
	public static float ORB_LVL3_MULTIPLIER = 0.15f;
	
	public static int MAX_LEVEL = 30;
	public static int POINTS_PER_LEVEL = 3;
	public static int LEADER_PV_MULTIPLIER = 4;
	public static int LEADER_PF_MULTIPLIER = 4;
	public static int LEADER_STAT_EFFECT_MULTIPLIER = 1;
	public static int LEADER_POINTS_MULTIPLIER = 1;
	
	public static int STAT_MAX_CAPACITY = 45;
	
	public static int BASE_ACCURACY_VALUE = 60;
	public static int BASE_CRITICAL_VALUE = 5;
	
	
	//files
	public static String JSON_PATH = "files/json/";
	public static String ICONS_PATH = "files/icons/";
}
