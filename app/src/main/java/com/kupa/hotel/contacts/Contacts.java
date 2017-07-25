package com.kupa.hotel.contacts;

import android.os.Environment;

import com.kupa.hotel.R;


/**
 * Created by HM on 2017/3/14 21:46
 */

public class Contacts {

    public static String DEFAULT_IP = "192.168.0.195";
    public static String DEFAULT_PATH = "smb://" + DEFAULT_IP + "/";
    public static String DEFAULT_UAERNAME = "kupaworld";
    public static String DEFAULT_PASSWORD = "kupa1806";
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().toString() + "/KupaTv";
    public static String DEFAULT_SMB_PATH = "smb://" + Contacts.DEFAULT_UAERNAME + ":" + Contacts.DEFAULT_PASSWORD + "@"
            + Contacts.DEFAULT_IP + "/video/";
    public static String SHENZHEN_CITYID = "101280601";
    public static final String CONTENT_TYPE = "text/html; charset=\"utf-8\"";
    public static final String SEARCH_ALLMOVIE_BYTYPE = "http://www.kupaworld.cn:1226/HotelTV/loadMovieServlet";
    /* 预设的组合快捷键数组:菜单键+上 */
    public static final String[] keyStrings = {"KEYCODE_MENU", "KEYCODE_DPAD_UP"};
    /* 系统设置的包名和类名 */
    public static final String packageName = "com.android.settings";
    public static final String className = "com.android.settings.Settings";

    public static final String[] mDataList = new String[]{
            "CCTV1高清", "香港卫视", "CHC家庭影院", "深圳卫视", "湖南卫视", "CCTV3高清", "CCTV5高清",
            "CCTV6高清", "CCTV15",
    };
    public static final String[] mUrlList = new String[]{
            "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8",
            "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8",
            "http://182.245.29.105:92/376/376.m3u8",
            "http://182.245.29.105:92/190/190.m3u8",
            "http://182.245.29.105:92/125/125.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8",
            "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8",
            "http://h5live.gslb.cmvideo.cn/envivo_x/SD/cctv15/450/index.m3u8?msisdn=0455655485170&mdspid=&spid=699004&netType=1&sid=2202203578&pid=2028597139×tamp=20170425164812&SecurityKey=20170425164812&Channel_ID=1004_10010001005&ProgramID=608807408&ParentNodeID=-99&assertID=2202203578&encrypt=b4432309e8c79bf48d75a2113cdf2a97",
    };


    //推荐
    public static String[] GOODS_RECOMMEND_NAME = {
            "The original bottle of imported Chile honey Shirley choice red wine，750 ml",
            "Pick up at the beginning of sanhua～Flirting 3 pens gift box，To live a",
            "double cooked pork slices",
            "Sliced tomato fish soup",
            "Garlic spicy Fried shrimp",
            "Ancient peanut candy, Pure malt sugar boil, 300g",
            "Fruit tree smoked octopus foot piece of octopus foot piece of seafood snacks180g",
            "Grilled fillet snacks150g",
            "Yu's house / Tsao zi ba / Glutinous rice cakes / 100g*5",
            "Chaoshan loquat fruit, Chaoshan candied fruit dried fruit flavor, Guangdong specialty",
            "Wenzhou city gift Ou mandarin orange honey refined 500g",

    };
    public static String[] GOODS_RECOMMEND_PRICE = {
            "￥ 79.00",
            "￥ 298.00",
            "￥ 26.00",
            "￥ 32.00",
            "￥ 68.00",
            "￥ 33.80",
            "￥ 25.80",
            "￥ 28.80",
            "￥ 28.80",
            "￥ 36.80",
            "￥ 38.80",

    };
    public static int[] GOODS_RECOMMEND_PICTURE = {
            R.mipmap.goods_wine_one, R.mipmap.goods_wine_two, R.mipmap.goods_repast_one, R.mipmap.goods_repast_two, R.mipmap.goods_repast_three,
            R.mipmap.goods_snacks_one, R.mipmap.goods_snacks_two, R.mipmap.goods_snacks_three, R.mipmap.goods_specialty_nine,
            R.mipmap.goods_specialty_ten, R.mipmap.goods_specialty_eleven
    };


    //烟酒
    public static String[] GOODS_TOBACCO_NAME = {
            "The original bottle of imported Chile honey Shirley choice red wine，750 ml",
            "Pick up at the beginning of sanhua～Flirting 3 pens gift box，To live a",
            "Tanya chua visit France imported echo park divine shield clay dry red wine",
            "German beer imported beer Mr. Odinga beer white 500 ml beer * 24 to listen to the whole case",
            "Ignition point liquor fireworks 38 degrees luzhou-flavor 38 degrees 500 ml",
            "JHBROS (home and brothers) tower 375 ml of whisky",
            "JHBROS (home and brothers) B - 15 160 ml x 2 of whisky",
            "Peter Noel pernol 2012 Pittnauer Pannobile 2012",
            "French library castle red bordeaux AOC fruity flavor Paris agricultural competition gold medal",
            "Flag of ice wine Tschida Eiswein",
            "Sao Paulo, Argentina wine bingosho bara bottle of imported high-grade red wine Add 750 ml FengSu o dry red wine",
            "[RED] RED eagle jarvis RED wine RED EMPIRE",
            "Di sparkling wine 】 【 prosser g DIAMA PROSECCO Italian sparkling wine",
            "[red] 2010 CASK520 casco 520 red wine",
            "Love season rose high bubble wine in Italy The original bottle of imported low ms sparkling wine sparkling wine champagne",
            "Puer tea smoke Do not contain nicotine YanZhi ChaYan tea to drink",
    };
    public static String[] GOODS_TOBACCO_PRICE = {
            "￥ 79.00",
            "￥ 298.00",
            "￥ 488.00",
            "￥ 269.00",
            "￥ 79.00",
            "￥ 98.00",
            "￥ 98.00",
            "￥ 618.00",
            "￥ 138.00",
            "￥ 599.00",
            "￥ 298.00",
            "￥ 128.00",
            "￥ 358.00",
            "￥ 1680.00",
            "￥ 68.00",
            "￥ 199.00",
    };
    public static int[] GOODS_TOBACCO_PICTURE = {
            R.mipmap.goods_wine_one, R.mipmap.goods_wine_two, R.mipmap.goods_wine_three,
            R.mipmap.goods_wine_four, R.mipmap.goods_wine_five, R.mipmap.goods_wine_six,
            R.mipmap.goods_wine_seven, R.mipmap.goods_wine_eight, R.mipmap.goods_wine_nine,
            R.mipmap.goods_wine_ten, R.mipmap.goods_wine_eleven, R.mipmap.goods_wine_twelve,
            R.mipmap.goods_wine_thirteen, R.mipmap.goods_wine_fourteen, R.mipmap.goods_wine_fiveteen,
            R.mipmap.goods_wine_sixteen
    };

    //餐饮
    public static String[] GOODS_REPAST_NAME = {
            "Pot bag meat",
            "Sliced tomato fish soup",
            "Garlic spicy Fried shrimp",
            "Roti prata vegetable egg tarts",
            "XO sauce im crispy Fried noodles",
            "Moo shu pork",
            "Oats rice cake",
            "FIG lily lotus seed sugar water",
            "The rainbow of black pizza",
            "American chicken soup",
            "Thai curry crab",
            "Italian pouch meat",
            "Boiled meat",
            "The bean curd",
            "Pine mushroom stewed chicken",
            "Braised mutton",
            "Pineapple black bears meat",
            "Little stupid chicken braise a mushroom",
            "Shanghai Fried stinky tofu",
            "Mr Mourinho fruit and vegetable juice drinks 330ml",
    };
    public static String[] GOODS_REPAST_PRICE = {
            "￥ 26.00",
            "￥ 32.00",
            "￥ 68.00",
            "￥ 24.00",
            "￥ 33.00",
            "￥ 28.00",
            "￥ 19.00",
            "￥ 19.00",
            "￥ 48.00",
            "￥ 52.00",
            "￥ 58.00",
            "￥ 29.00",
            "￥ 58.00",
            "￥ 32.00",
            "￥ 26.00",
            "￥ 52.00",
            "￥ 68.00",
            "￥ 32.00",
            "￥ 64.00",
            "￥ 26.00",
            "￥ 199.00"
    };
    public static int[] GOODS_REPAST_PICTURE = {
            R.mipmap.goods_repast_one, R.mipmap.goods_repast_two, R.mipmap.goods_repast_three,
            R.mipmap.goods_repast_four, R.mipmap.goods_repast_five, R.mipmap.goods_repast_six,
            R.mipmap.goods_repast_seven, R.mipmap.goods_repast_eight, R.mipmap.goods_repast_nine,
            R.mipmap.goods_repast_ten, R.mipmap.goods_repast_eleven, R.mipmap.goods_repast_twelve,
            R.mipmap.goods_repast_thirteen, R.mipmap.goods_repast_fourteen, R.mipmap.goods_repast_fiveteen,
            R.mipmap.goods_repast_sisteen, R.mipmap.goods_repast_seventeen, R.mipmap.goods_repast_eighteen,
            R.mipmap.goods_repast_nineteen, R.mipmap.goods_repast_twenty, R.mipmap.goods_repast_twenty_one,
    };

    //零食
    public static String[] GOODS_SNACKS_NAME = {
            "Ancient peanut candy Pure malt sugar boil 300g",
            "Fruit tree smoked octopus foot piece of octopus foot piece of seafood snacks 180g",
            "Grilled fillet snacks150g",
            "Freeze-dried strawberries dried Strawberry flat piece of dried fruit snacks office 25g*5包",
            "Comprehensive natural dried fruits 】 【 35 g/fruit chips bags of 6 kinds of fruit flavor",
            "Longjing tea cake Jiangnan character pastry 200g",
            "Manual nougat biscuits 200 g plain / / cranberry matcha Taiwan flavor snacks snacks",
            "Cocoa wine stain cherry 7 big cookie",
            "Big chocolate cookies 50 g * 7",
            "Jasmine on bread Mo flower Lingering fragrance around the pillow",
            "Macedonia import finch hazelnut paste biscuit 80 g",
            "Macedonia import finch jam sandwich biscuit 165 g",
            "Ryohin keikaku shop dried mango 108 single bag",
            "Long to French milk flavor small bread is 440 g",
            "Pleased lang cici juice jelly great preferential 150 g * 5 / nostalgia and food",
            "Grass bouquet smell Taiwan characteristic cakes Pineapple cakes, 300 g",
    };
    public static String[] GOODS_SNACKS_PRICE = {
            "￥ 33.80",
            "￥ 25.80",
            "￥ 28.80",
            "￥ 39.50",
            "￥ 13.90",
            "￥ 32.00",
            "￥ 24.80",
            "￥ 59.00",
            "￥ 49.00",
            "￥ 22.00",
            "￥ 11.90",
            "￥ 17.90",
            "￥ 18.90",
            "￥ 10.80",
            "￥ 12.90",
            "￥ 29.80",
    };
    public static int[] GOODS_SNACKS_PICTURE = {
            R.mipmap.goods_snacks_one, R.mipmap.goods_snacks_two, R.mipmap.goods_snacks_three,
            R.mipmap.goods_snacks_four, R.mipmap.goods_snacks_five, R.mipmap.goods_snacks_six,
            R.mipmap.goods_snacks_seven, R.mipmap.goods_snacks_eight, R.mipmap.goods_snacks_nine,
            R.mipmap.goods_snacks_ten, R.mipmap.goods_snacks_eleven, R.mipmap.goods_snacks_twelve,
            R.mipmap.goods_snacks_thirteen, R.mipmap.goods_snacks_fourteen, R.mipmap.goods_snacks_fiveteen,
            R.mipmap.goods_snacks_sixteen
    };

    //特产
    public static String[] GOODS_SPECIALTY_NAME = {
            "Green product is the northeast traditional manual big pancake Sweet and easy to digest Zero to add 4 / bag (155 g)",
            "Blindly ingredients such as Chaoshan hand soil pork spicy Manual peasant homemade Chaoshan speciality 180 g",
            "Golden island Yuan BeiYao column scallops dry cooking porridge No salt scallops Seafood dry 200 g",
            "Such as blindly | hawthorn ball by hand Shirley balls chaoshan specialty snacks snacks 280 g",
            "Golden island Xiamen non dry oyster dry Oyster dry Seafood aquaculture dry 180 g",
            "Golden island Wild shrimp dry Pale gold dried shrimp shrimp shrimp Seafood dry 150 g",
            "Xiao qi Chen halide Spicy tripe Snacks sauced meat 150 grams",
            "Yu home/brown sugar rice cake/brown sugar rice cakes / 500 g",
            "Yu home/tsao zi ba/glutinous rice cakes / 100 g * 5",
            "Chaoshan loquat fruit Chaoshan candied fruit dried fruit flavor Guangdong specialty",
            "Wenzhou city gift Ou mandarin orange honey refined 500 g",
    };
    public static String[] GOODS_SPECIALTY_PRICE = {
            "￥ 8.00",
            "￥ 29.00",
            "￥ 158.00",
            "￥ 16.00",
            "￥ 28.00",
            "￥ 60.00",
            "￥ 29.80",
            "￥ 16.80",
            "￥ 28.80",
            "￥ 36.00",
            "￥ 38.00",
    };
    public static int[] GOODS_SPECIALTY_PICTURE = {
            R.mipmap.goods_specialty_one, R.mipmap.goods_specialty_two, R.mipmap.goods_specialty_three,
            R.mipmap.goods_specialty_four, R.mipmap.goods_specialty_five, R.mipmap.goods_specialty_six,
            R.mipmap.goods_specialty_seven, R.mipmap.goods_specialty_eight, R.mipmap.goods_specialty_nine,
            R.mipmap.goods_specialty_ten, R.mipmap.goods_specialty_eleven
    };

    //礼品
    public static String[] GOODS_PRESENT_NAME = {
            "Plant has Hearten four cans of gift box",
            "Small dome T1 portable tea set gift suit sandstone glaze tea box version (black) geek",
            "Farm produce pure natural honey gift gift gift box Wild litchi honey volcano",
            "Pure manual embroidery cloth Chinese business gifts",
            "Such as bees to honey Sichuan JTG rural honey 618 g",
            "Comb heart box comb suit your loving heart",
    };
    public static String[] GOODS_PRESENT_PRICE = {
            "￥ 158.00",
            "￥ 368.00",
            "￥ 189.00",
            "￥ 296.00",
            "￥ 69.00",
            "￥ 196.00",
    };
    public static int[] GOODS_PRESENT_PICTURE = {
            R.mipmap.goods_present_one, R.mipmap.goods_present_two, R.mipmap.goods_present_three,
            R.mipmap.goods_present_four, R.mipmap.goods_present_five, R.mipmap.goods_present_six,
    };

    //设备
    public static String[] GOODS_EQUIPMENT_NAME = {
            "Full hd glass lens intelligent micro KUPA F1 F1",
            "Interactive smart touch all-in-one PC KUPA P1",
            "KUPA full hd laser projection TV  P2",
            "Micro short focal projection KUPA P3",
    };
    public static String[] GOODS_EQUIPMENT_PRICE = {
            "New products listed",
            "￥ 29800.00",
            "￥ 39800.00",
            "New products listed",
    };
    public static int[] GOODS_EQUIPMENT_PICTURE = {
            R.mipmap.goods_equipment_one, R.mipmap.goods_equipment_two, R.mipmap.goods_equipment_three,
            R.mipmap.goods_equipment_four
    };
}
