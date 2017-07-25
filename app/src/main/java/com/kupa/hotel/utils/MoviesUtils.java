package com.kupa.hotel.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.kupa.hotel.R;
import com.kupa.hotel.entity.Movie;
import com.kupa.hotel.entity.MovieInfo;
import com.kupa.hotel.entity.MovieType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HM on 2017/5/4 14:50
 */

public class MoviesUtils {

    public static List<MovieInfo> getMovieInfo() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo
                info = new MovieInfo(
                "Angry Birds",
                "United States | Finland",
                "A group of birds that can't fly, huddled on a tropical island, have peace and quiet.In the game a few classic image became the protagonist of the film, respectively is angry red bird (Jason Sue dyche), speed the bird chuck (josh gerd), bomb (Danny McBride, resistance).When the mysterious green pig landed on the island",
                "Ferger riley",
                "Jason Sudeikis" +
                        "Josh Gad" +
                        "Danny McBride" +
                        "Maya Rudolph",
                97,
                "Animation / Plot",
                6.9,
                2016,
                R.mipmap.test_fennudexiaoniao,
                "/storage/sdcard/Movies/愤怒的小鸟.mp4",
                "/storage/sdcard/Movies/愤怒的小鸟正片.mp4"
        );
        list.add(info);
        info = new MovieInfo(
                "Biochemical crisis: final chapter",
                "America",
                "Alice was in danger when she was betrayed by westcott in Washington, d.c., and the human race almost lost hope.As the only survivor, also is the human last line of defence against zombie army, Alice must go back to a nightmare start place of raccoon city, redeem human justice mission to save the world.The return to the story of the goddess, raccoon, and former friends against zombies and the latest variety of monsters, has launched a thrilling final showdown with the umbrella company.",
                "Paul Anderson",
                "Milla jovovich" +
                        "Iain Gray" +
                        "Elle ratt" +
                        "Al Anderson",
                99,
                "Action / Science fiction",
                6.2,
                2016,
                R.mipmap.test_shenghuaweiji,
                "/storage/sdcard/Movies/生化危机.mp4",
                "/storage/sdcard/Movies/生化危机正片.mp4"
        );
        list.add(info);
        info = new MovieInfo(
                "Big Hero 6",
                "America",
                "Film story focuses on a robot to master infant prodigy hom and profound friendship between pneumatic robot to light, in the city they inadvertently found himself living in San Francisco crime after the crisis, hom to the good friend must be combined to light \"super disease express\" royal elder sister, god \"fly knife kitchen god\" mustard boundless, \"chemical witch\" HaNi lemon and otaku \"monster\" Fred for help.He decided to solve the mystery and save the city, so he transformed the team that never beat a crime into a high-tech hero, \"super marines\".",
                "Chris Williams",
                "Ryan Potter" +
                        "Scott Adsit" +
                        "T. j. Miller" +
                        "Jamie Chung",
                102,
                "Action / Adventure",
                8.2,
                2014,
                R.mipmap.guzhuangwuxia_chaonengluzhandui,
                "/storage/sdcard/Movies/超能陆战队.mp4",
                "/storage/sdcard/Movies/超能陆战队正片.mp4"
        );
        list.add(info);
        info = new MovieInfo(
                "Operation Mekong",
                "Chinese",
                "On October 5, 2011, two Chinese merchant ships were attacked in the golden triangle of the Mekong river, killing all 13 Chinese sailors, and the Thai police found 90, 000 meth from the boat.The news came back home and was shocked.In order to find out the truth, yunnan drug team grow just accepted special tasks (zhang), lead a warrior fighting team into Thailand, and latent agents in Thailand meet new wu (peng), two of them to conduct deep golden triangle investigations.After the investigation, it was found that there was a lot of suspicion behind the case. The real killer was not only free from the law, but also intended to use drugs to make a bigger conspiracy after the murder of the innocent Chinese crew...The two men decided to do whatever it takes to win the real crime, fight drug crimes, and get justice for the innocent!",
                "Lin Chaoxian",
                "Zhang Hanyu" +
                        "Peng Yuyan" +
                        "Chen Baoguo" +
                        "Sun Chun",
                124,
                "Action / Adventure",
                7.6,
                2016,
                R.mipmap.movie_meigonghexingdong,
                "/storage/sdcard/Movies/湄公河行动.mp4",
                "/storage/sdcard/Movies/湄公河行动正片.mp4"
        );
        list.add(info);

        info = new MovieInfo(
                "3D-MV",
                "South Korea",
                "Girlhood, Girls' Generation) is South Korea SM entertainment co., LTD., launched in 2007, women's popular singing group, by Mr Yan, Jessica, sunny, tiffany, hyoyeon, QuanYu and seo joo hyun, soo young, Lin Yun son of 9 people, is in the form of eight people for performing activities.On August 5, 2007, the debut single, \"the world again,\" was released and the first regular album, Girls' Generation, was released on 1 November.On January 7, 2009, the debut mini-album Gee was popular in the music world.The second mini-album, \"Genie,\" was released on June 29.",
                "South Korean director",
                "Korean dance company",
                3,
                "Dance / Music",
                9.0,
                2017,
                R.mipmap.mv_3d,
                "/storage/sdcard/Movies/3DMTV.mp4",
                "/storage/sdcard/Movies/3DMTV.mp4"
        );
        list.add(info);


        info = new MovieInfo(
                "Star Wars: rogue one",
                "America",
                "It was the time of the collapse of the republican system and the collapse of the jedi.\n" +
                        "Since palpatine in the so-called new order after the reorganization of the galactic republic galactic empire, the people's rebellion increase gradually and form various insurgent groups, including horse mask moth (Jin Niwei, Mr Reilly) under the leadership of the Rebel Alliance (Rebel Alliance).\n" +
                        "The rebellion has forced the galactic empire to come up with a variety of countermeasures against the insurgency, the most violent of which is the Death Star.\n" +
                        "Kim urban cable (Jyn Erso, felicity mix of Jones) is a wild trouble girl, into the hands of the rebel alliance, the horse took a fancy to Kim meng moth eritrea, and choose to do steal the death star plans.\n" +
                        "But for rebel, they only know empire had a weapon of mass destruction, the task of unknown, let it become very hard, Kim urban cable will and her partners on a dangerous journey.",
                "Gareth Edwards",
                "Felicity Jones" +
                        "Diego Luna" +
                        "Donnie Yen" +
                        "Ben mendelson",
                134,
                "Action / Adventure",
                7.5,
                2016,
                R.mipmap.guzhuangwuxia_xingqiudazhan,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Kung-Fu Yoga",
                "Chinese | India",
                "Professor Jack (Jackie chan) has not stepped out of school for years, and the President and his students are dubious about the history of his work.After a lecture, an Indian girl found Jack and attracted Jack with a map of the millennium.So Jack and his friend sheng wang (never know), the son of ta light (lay) of \"treasure team\", as the \"mysterious jewel\" adventure, in dubai and local tyrants racing, dive into the mysterious ice cave in Iceland, war hyenas in India, and India aristocrats dance, comedy, upgrade again, laugh all over the world.",
                "Stanley Tong",
                "Jackie Chan" +
                        "Aarif Lee" +
                        "Lay Zhang" +
                        "Mother the messiah",
                108,
                "Action / Comedy",
                6.0,
                2017,
                R.mipmap.aiqingxiju_gongfuyujia,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Come",
                "America",
                "Twelve mysterious UFO came to earth, played by Amy Adams linguist Dr Louise class and Jeremy renner theoretical physicist Ian donnelly's government sent to investigate the alien object class.In the process, Louise tries to communicate with the aliens by establishing language. In the process, she has some mysterious illusions, which means what they mean, waiting for them to resolve.",
                "Denis villeneuve",
                "Amy Adams" +
                        "Jeremy Renner" +
                        "Forest Whitaker" +
                        "Michael Stuhlbarg",
                116,
                "Science fiction / Suspense",
                7.5,
                2016,
                R.mipmap.guzhuangwuxia_jianglin,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Master's sword",
                "Chinese",
                "\"Shock wave vertical and horizontal three miles, sword of light cold ten jiuzhou\", excalibur heights three young master xie (update), Lin jing was absolutely brilliant, unbeaten 10 years after thousands of wars, be considered one sword \"god\".Legendary swordsman yan xiii (he rundong) has always been the target of xie xiaofeng, suffering from the sword road, finally at the edge of life and death to understand the world of make the gods cry's \"thirteen swords\".For a moment, the river was boiling, however, it was the coffin of the three young master xie xiaofeng, who greeted him when she arrived at the sword hill.Yan big disappointed. Because of the loss of rivals, the mysterious woman MuRongQiuDi (jiang yan) appeared and told yan thirteen, xie did not die, to find his decisive battle, yan thirteen will have to kill someone for her...",
                "Derek Yee",
                "Kenny" +
                        "Peter Ho" +
                        "Jiang Yiyan" +
                        "Jiang Mengjie",
                90,
                "Emprise / Action",
                6.5,
                2016,
                R.mipmap.guzhuangwuxia_sanshaoyedejian,
                "预告片",
                "正片"
        );
        list.add(info);

        info = new MovieInfo(
                "Contracts for men and women",
                "Chinese",
                "Film tells the story of an insurance company CEO Ye Jin (sammi cheng), is the heart not anger and the hegemony of female President, to the love of despair she thought only to the child's feelings is eternal, decided to borrow fine children.After rigorous screening, handsome and unruly Courier xiao bo (zhang xiaoquan) unexpectedly becomes the father's final choice.Xiao bo, who has the hard words, signed the contract of \"abnormal\".And the story is just beginning...",
                "Liu Guonan",
                "Sammi Cheng" +
                        "Joseph Chang" +
                        "Joyce Feng" +
                        "Suet Lam",
                96,
                "Affection / Plot",
                5.9,
                2017,
                R.mipmap.aiqingxiju_heyuenannv,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Monk Comes Down The Mountain",
                "Chinese",
                "The inexperienced little monk, because the hunger to leave Taoist temple down from the mountain, in the dazzling lights of the world of mortals, he with a pure heart to face everything, only to find that how different this world with his imagination, he realized that the near the mountain before the teacher said: \"by hook or by crook is a hero, don't change the original true hero\".",
                "Chen Kaige",
                "Wang Baoqiang" +
                        "Aaron Kwok" +
                        "Zhang Zhen" +
                        "Lin Chi-ling",
                120,
                "Action / Plot",
                6.2,
                2015,
                R.mipmap.guzhuangwuxia_daoshixiashan,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Train to Busan",
                "South Korea",
                "The story of a KTX train bound for busan was hit by a zombie attack, and people on the train struggled to survive with zombies and unknown viruses.This is South Korea's first zombie apocalypse movie.The film is invested in 10 billion Korean currency.",
                "Yeun Sang-ho",
                "Gong Yoo" +
                        "Yu-mi Jeong" +
                        "Ma Tong-seok" +
                        "Jin Xiuan",
                118,
                "Action / Horror",
                7.7,
                2016,
                R.mipmap.kongbuxuanyi_fushaning,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Edge of Tomorrow",
                "America",
                "The film depicts a war against aliens, and the protagonist, cage, who has never been trained in military training, is sent to the front line, a suicide mission.At the moment of his death, he accidentally acquired the ability to travel through time and space, allowing him to repeat the cycle of life and death, and to return to the battlefield again and again.And every time he dies, cage's fighting strength increases one point...",
                "Doug Liman",
                "Tom Cruise" +
                        "Emily Blunt" +
                        "Bill Paxton" +
                        "Eye' Moody",
                113,
                "Action / Science fiction",
                8.0,
                2014,
                R.mipmap.guzhuangwuxia_mingribianyuan,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Your name",
                "Japan",
                "Story background in comes at a time when a millennium comet visiting Japanese, living in Japan town of female high school students 3 leaf for's father served as mayor's election campaign, and feel boring old custom, family shrine to the big city filled with visions of her, even fantasizing about \"next life make I was born in Tokyo, a handsome boy!\"Suddenly one day I made a dream to become a boy, in a strange room, in the face of strange friends, and the streets of Tokyo.Although confused, the young girl is still full of joy in coming to Tokyo.Meanwhile, taki, a senior high school student living in Tokyo, had a strange dream, becoming a high school girl in a remote mountain town he had never visited before.The young man meets each other in a dream, and looks for each other with the belief that \"wherever you are in the world, I will meet you\".Yes, this is the miracle story of the exchange of the body, so what is the reason for this wonderful dream of young man and girl?And what would happen to them?",
                "Makoto Shinkai",
                "Ryunosuke Kamiki" +
                        "Mone Kamishiraishi" +
                        "Long ZeYa beauty" +
                        "Etsuko Ichihara",
                106,
                "Animation / Plot",
                8.4,
                2016,
                R.mipmap.donghuadaquan_nidemingzi,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Heartache",
                "America | Finland",
                "Julie baker devoutly believed that three things: a tree is holy (especially her favorite buttonwood), raised her in the backyard chickens born out of the egg is the health, and one day she will kiss and bryce Ross.In the second grade, Julie's heart was hit by him when she saw bryce's blue eyes.Unfortunately, bryce never felt it to her.Also, he thought Julie was a little weird, how could anyone treat chickens and trees as fun?",
                "Rob Reiner",
                "Madeleine Carroll" +
                        "Karan McLiffe" +
                        "Aidan quinn" +
                        "Rebecca De Mornay",
                90,
                "Love / Comedy",
                8.6,
                2010,
                R.mipmap.aiqingxiju_pengranxindong,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Fantasy forest",
                "America",
                "Mowgli is a human boy raised by a pack of wolves (new actor Neil sisi), and the film revolves around his forest adventures.Shelley khan, a wounded tiger (voiced by idris Elba), has vowed to weed out mowgli.To escape capture, qualicoat follow strict mentor MAO panthers and sheila (Ben kingsley) and the freedom of friends of the brown bear subaru (bill Murray), set foot on a wonderful journey of self-discovery.In this trip, MAO Cleveland met with some of his sinister woodland creatures, including python card (scarlett johansson), her hair and eye contact with the voice of fascinating puzzle in Cleveland;Then there is the smooth-talking king louie (voiced by Christopher warken), who tries to coerce maury into handing over the hallucinogenic \"red flower\" -- the secret of the fire.When the forest is no longer a safe home, where does maukley go?And the great adventure of a lifetime has just begun...",
                "Jon Favreau",
                "Neil sisi" +
                        "Bill Murray" +
                        "Ben Kingsley" +
                        "Idris Elba",
                105,
                "Adventure / Plot",
                7.7,
                2016,
                R.mipmap.guzhuangwuxia_qihuansenlin,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "The Fast and the Furious 6",
                "America",
                "Dominic (vin diesel) is enjoying himself after leading his partner a $100 million bill, but they can't go back to their country because of a criminal record.Hobbes was looking at this time a mercenary in 12 countries escaping crime criminal group, he thought of a plan of be satisfactory to both sides: recruiting Dominic gangs and mercenary group in London on racing battle, no matter how the two side of negative crime will be written off.",
                "Lin Yi his",
                "Vin Diesel" +
                        "Paul Walker" +
                        "Dwayne Johnson" +
                        "Luke Evans",
                130,
                "Action / Crime",
                7.8,
                2013,
                R.mipmap.aiqingxiju_suduyujiqing7,
                "预告片",
                "正片"
        );
        list.add(info);
        info = new MovieInfo(
                "Interstellar through",
                "America",
                "Based on the theory of the theoretical physicist Kip Thorne, the film tells the story of a group of explorers travelling through wormholes about time travel and parallel universes.The story of \"interstellar\" tells the story of the future of the earth's climatic environment and the severe shortage of food, which makes it difficult for humans to survive.Matthew McConaughey's character, cooper and Anne hathaway, has been selected as part of a plan to save the future of humanity, and must travel outside the solar system to find habitable planets.",
                "Christopher Nolan",
                "Matthew McConaughey" +
                        "Anne Hathaway" +
                        "Jessica Chastain" +
                        "Michael Caine",
                169,
                "Animation / Plot",
                8.4,
                2014,
                R.mipmap.guzhuangwuxia_xingjichuanyue,
                "预告片",
                "正片"
        );
        list.add(info);
        return list;
    }

    //古装武侠
    public static List<MovieInfo> getGuZhuang() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo info = new MovieInfo("Just Another Margin", R.mipmap.guzhuangwuxia_dahuatianxian);
        list.add(info);
        info = new MovieInfo("Monk Comes Down The Mountai", R.mipmap.guzhuangwuxia_daoshixiashan);
        list.add(info);
        info = new MovieInfo("Di Renjie", R.mipmap.guzhuangwuxia_direnjie);
        list.add(info);
        info = new MovieInfo("Fei-hung Wong", R.mipmap.guzhuangwuxia_huangfeihong);
        list.add(info);
        info = new MovieInfo("Rurouni Kenshin", R.mipmap.guzhuangwuxia_langkejianxin);
        list.add(info);
        info = new MovieInfo("Flying Swords of Dragon Gate", R.mipmap.guzhuangwuxia_longmenfeijia);
        list.add(info);
        info = new MovieInfo("The Legend of Qin", R.mipmap.guzhuangwuxia_qinshimingyue);
        list.add(info);
        info = new MovieInfo("Gang robbery", R.mipmap.guzhuangwuxia_qundao);
        list.add(info);
        info = new MovieInfo("Master's sword", R.mipmap.guzhuangwuxia_sanshaoyedejian);
        list.add(info);
        info = new MovieInfo("The master", R.mipmap.guzhuangwuxia_shifu);
        list.add(info);
        info = new MovieInfo("The big four lobsterman", R.mipmap.guzhuangwuxia_sidamingbu);
        list.add(info);
        info = new MovieInfo("Surprise", R.mipmap.guzhuangwuxia_wanwanmeixinagdao);
        list.add(info);
        info = new MovieInfo("Crouching Tiger, Hidden Dragon", R.mipmap.guzhuangwuxia_wohucanglong);
        list.add(info);
        info = new MovieInfo("Brotherhood of Blades", R.mipmap.guzhuangwuxia_xiuchundao);
        list.add(info);
        return list;
    }

    //古装武侠
    public static List<MovieInfo> getKeHuan() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo info = new MovieInfo("X-Men", R.mipmap.kehuandapian_xzhanjing);
        list.add(info);
        info = new MovieInfo("Monk Comes Down The Mountain", R.mipmap.guzhuangwuxia_daoshixiashan);
        list.add(info);
        info = new MovieInfo("Di Renjie", R.mipmap.guzhuangwuxia_direnjie);
        list.add(info);
        info = new MovieInfo("Fei-hung Wong", R.mipmap.guzhuangwuxia_huangfeihong);
        list.add(info);
        info = new MovieInfo("Rurouni Kenshin", R.mipmap.guzhuangwuxia_langkejianxin);
        list.add(info);
        info = new MovieInfo("Flying Swords of Dragon Gate", R.mipmap.guzhuangwuxia_longmenfeijia);
        list.add(info);
        info = new MovieInfo("The Legend of Qin", R.mipmap.guzhuangwuxia_qinshimingyue);
        list.add(info);
        info = new MovieInfo("Gang robbery", R.mipmap.guzhuangwuxia_qundao);
        list.add(info);
        info = new MovieInfo("Master's sword", R.mipmap.guzhuangwuxia_sanshaoyedejian);
        list.add(info);
        info = new MovieInfo("The master", R.mipmap.guzhuangwuxia_shifu);
        list.add(info);
        info = new MovieInfo("The big four lobsterman", R.mipmap.guzhuangwuxia_sidamingbu);
        list.add(info);
        info = new MovieInfo("Surprise", R.mipmap.guzhuangwuxia_wanwanmeixinagdao);
        list.add(info);
        info = new MovieInfo("Crouching Tiger, Hidden Dragon", R.mipmap.guzhuangwuxia_wohucanglong);
        list.add(info);
        info = new MovieInfo("Brotherhood of Blades", R.mipmap.guzhuangwuxia_xiuchundao);
        list.add(info);
        return list;
    }

    //动画
    public static List<MovieInfo> getDonghua() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo info = new MovieInfo("Angry Birds", R.mipmap.test_fennudexiaoniao);
        list.add(info);
        info = new MovieInfo("your name", R.mipmap.donghuadaquan_nidemingzi);
        list.add(info);
        return list;
    }

    //动作
    public static List<MovieInfo> getDongzuo() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo info = new MovieInfo("Limit agents", R.mipmap.dongzuomaoxian_jixiantegong);
        list.add(info);
        info = new MovieInfo("Resident Evil", R.mipmap.dongzuomaoxian_shenghuaweiji);
        list.add(info);
        info = new MovieInfo("Di Renjie", R.mipmap.guzhuangwuxia_direnjie);
        list.add(info);
        info = new MovieInfo("Fei-hung Wong", R.mipmap.guzhuangwuxia_huangfeihong);
        list.add(info);
        info = new MovieInfo("Rurouni Kenshin", R.mipmap.guzhuangwuxia_langkejianxin);
        list.add(info);
        info = new MovieInfo("Flying Swords of Dragon Gate", R.mipmap.guzhuangwuxia_longmenfeijia);
        list.add(info);
        info = new MovieInfo("The Legend of Qin", R.mipmap.guzhuangwuxia_qinshimingyue);
        list.add(info);
        info = new MovieInfo("Gang robbery", R.mipmap.guzhuangwuxia_qundao);
        list.add(info);
        info = new MovieInfo("Master's sword", R.mipmap.guzhuangwuxia_sanshaoyedejian);
        list.add(info);
        info = new MovieInfo("The master", R.mipmap.guzhuangwuxia_shifu);
        list.add(info);
        info = new MovieInfo("The big four lobsterman", R.mipmap.guzhuangwuxia_sidamingbu);
        list.add(info);
        info = new MovieInfo("Surprise", R.mipmap.guzhuangwuxia_wanwanmeixinagdao);
        list.add(info);
        info = new MovieInfo("Crouching Tiger, Hidden Dragon", R.mipmap.guzhuangwuxia_wohucanglong);
        list.add(info);
        info = new MovieInfo("Brotherhood of Blades", R.mipmap.guzhuangwuxia_xiuchundao);
        list.add(info);
        return list;
    }

    //古装武侠
    public static List<MovieInfo> getAiQing() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo info = new MovieInfo("La La Land", R.mipmap.aiqingxiju_aiyuezhicheng);
        list.add(info);
        info = new MovieInfo("A Little Thing Called Love", R.mipmap.aiqingxiju_chulianzhejianxiaoshi);
        list.add(info);
        info = new MovieInfo("A Chinese Odyssey", R.mipmap.aiqingxiju_dahuaxiyou);
        list.add(info);
        info = new MovieInfo("Buddies in India", R.mipmap.aiqingxiju_danaotianzhu);
        list.add(info);
        info = new MovieInfo("Kung-Fu Yoga", R.mipmap.aiqingxiju_gongfuyujia);
        list.add(info);
        info = new MovieInfo("Contracts for men and women", R.mipmap.aiqingxiju_heyuenannv);
        list.add(info);
        info = new MovieInfo("heartache", R.mipmap.aiqingxiju_pengranxindong);
        list.add(info);
        info = new MovieInfo("Feeling saint", R.mipmap.aiqingxiju_qingsheng);
        list.add(info);
        info = new MovieInfo("Soulmate", R.mipmap.aiqingxiju_qiyueyuansheng);
        list.add(info);
        info = new MovieInfo("Out Of This World", R.mipmap.aiqingxiju_shijiezhiwai);
        list.add(info);
        info = new MovieInfo("About Time", R.mipmap.aiqingxiju_shikongliannvren);
        list.add(info);
        info = new MovieInfo("Future father-in-law in son-in-law", R.mipmap.aiqingxiju_weishenmeshita);
        list.add(info);
        info = new MovieInfo("My Best Friend's Wedding", R.mipmap.aiqingxiju_wozuihaopengyoudehunli);
        list.add(info);
        info = new MovieInfo("Because of love", R.mipmap.aiqingxiju_yinweiai);
        list.add(info);
        info = new MovieInfo("Me Before You", R.mipmap.aiqingxiju_yujiannizhiqian);
        list.add(info);
        return list;
    }

    //历史
    public static List<MovieInfo> getLiShi() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo info = new MovieInfo("love", R.mipmap.lishizhuanji_ailian);
        list.add(info);
        info = new MovieInfo("Dozen tiger", R.mipmap.lishizhuanji_dahu);
        list.add(info);
        info = new MovieInfo("The Big Short", R.mipmap.lishizhuanji_dakongtou);
        list.add(info);
        info = new MovieInfo("Xuanzang", R.mipmap.lishizhuanji_datangxuanzang);
        list.add(info);
        info = new MovieInfo("Will the master weng", R.mipmap.lishizhuanji_dehuiwengzhu);
        list.add(info);
        info = new MovieInfo("First Lady", R.mipmap.lishizhuanji_diyifuren);
        list.add(info);
        info = new MovieInfo("The key sentence", R.mipmap.lishizhuanji_guanjianpanjue);
        list.add(info);
        info = new MovieInfo("RACE", R.mipmap.lishizhuanji_heiseshandian);
        list.add(info);
        info = new MovieInfo("Before the spy", R.mipmap.lishizhuanji_jiandiezhiqiao);
        list.add(info);
        info = new MovieInfo("Elvis & Nixon", R.mipmap.lishizhuanji_maowangyunikesong);
        list.add(info);
        info = new MovieInfo("Lost Cities", R.mipmap.lishizhuanji_mishizhicheng);
        list.add(info);
        info = new MovieInfo("The Free State Of Jones", R.mipmap.lishizhuanji_qiongsideziyouguodu);
        list.add(info);
        info = new MovieInfo("Plum flower pavilion", R.mipmap.lishizhuanji_taolihuage);
        list.add(info);
        info = new MovieInfo("Ip Man", R.mipmap.lishizhuanji_yewen);
        list.add(info);
        info = new MovieInfo("The Presidential Trail", R.mipmap.lishizhuanji_zongtongzhilu);
        list.add(info);
        info = new MovieInfo("Colonia Dignidad", R.mipmap.lishizhuanji_zunyanzhimingdi);
        list.add(info);
        return list;
    }

    //古装武侠
    public static List<MovieInfo> getKongBu() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo info = new MovieInfo("Ask the spirits curse", R.mipmap.kongbuxuanyi_bixianmozhou);
        list.add(info);
        info = new MovieInfo("Train to Busan", R.mipmap.kongbuxuanyi_fushaning);
        list.add(info);
        info = new MovieInfo("Estate of Unrest", R.mipmap.kongbuxuanyi_guizhai);
        list.add(info);
        info = new MovieInfo("A friend request", R.mipmap.kongbuxuanyi_haoyouqingqiu);
        list.add(info);
        info = new MovieInfo("Battle of Memories", R.mipmap.kongbuxuanyi_jiyidashi);
        list.add(info);
        info = new MovieInfo("The Terror Live", R.mipmap.kongbuxuanyi_kongbuzhibo);
        list.add(info);
        info = new MovieInfo("MAMA", R.mipmap.kongbuxuanyi_mam);
        list.add(info);
        info = new MovieInfo("The daughter of the priest", R.mipmap.kongbuxuanyi_mushizhinv);
        list.add(info);
        info = new MovieInfo("Twist to kill", R.mipmap.kongbuxuanyi_sharenmanhua);
        list.add(info);
        info = new MovieInfo("Raw", R.mipmap.kongbuxuanyi_shengchi);
        list.add(info);
        info = new MovieInfo("Christmas horror stories", R.mipmap.kongbuxuanyi_shengdanjiekongbuguyshi);
        list.add(info);
        info = new MovieInfo("Mysterious infection", R.mipmap.kongbuxuanyi_shenmiganran);
        list.add(info);
        info = new MovieInfo("Bilocation", R.mipmap.kongbuxuanyi_shuangshengling);
        list.add(info);
        info = new MovieInfo("Through the snow may find", R.mipmap.kongbuxuanyi_taxuexunmei);
        list.add(info);
        info = new MovieInfo("The heart is lifted with courage", R.mipmap.kongbuxuanyi_tezhexindiaozhedan);
        list.add(info);
        info = new MovieInfo("paranoia", R.mipmap.kongbuxuanyi_wangxiangzheng);
        list.add(info);
        info = new MovieInfo("Who am I", R.mipmap.kongbuxuanyi_woshishui);
        list.add(info);
        return list;
    }

    //古装武侠
    public static List<MovieInfo> getGeWu() {
        List<MovieInfo> list = new ArrayList<>();
        MovieInfo info = new MovieInfo("Les miserables", R.mipmap.remenjingxuan_beicanshijie);
        list.add(info);
        info = new MovieInfo("A Little Thing Called Love", R.mipmap.aiqingxiju_chulianzhejianxiaoshi);
        list.add(info);
        info = new MovieInfo("Julius Caesar\n", R.mipmap.remenjingxuan_kaisadadi);
        list.add(info);
        info = new MovieInfo("The Way We Dance", R.mipmap.remenjingxuan_kuangwupai);
        list.add(info);
        info = new MovieInfo("Love love songs such as", R.mipmap.remenjingxuan_lianlianruge);
        list.add(info);
        info = new MovieInfo("charm", R.mipmap.remenjingxuan_meihuo);
        list.add(info);
        info = new MovieInfo("Jack Goes Boating", R.mipmap.remenjingxuan_niuyueqiyuan);
        list.add(info);
        info = new MovieInfo("God helps girls", R.mipmap.remenjingxuan_shangdibangzhunvhai);
        list.add(info);
        info = new MovieInfo("Love to dance", R.mipmap.remenjingxuan_shiaiwu);
        list.add(info);
        info = new MovieInfo("The Rooftop", R.mipmap.remenjingxuan_tiantaiaiqingh);
        list.add(info);
        info = new MovieInfo("The tone of the perfect", R.mipmap.remenjingxuan_wanmeishengdiao);
        list.add(info);
        info = new MovieInfo("Step Up", R.mipmap.remenjingxuan_wuchuworensheng);
        list.add(info);
        info = new MovieInfo("Dance rebirth force", R.mipmap.remenjingxuan_wulichongsheng);
        list.add(info);
        info = new MovieInfo("dancer", R.mipmap.remenjingxuan_wunv);
        list.add(info);
        info = new MovieInfo("La La Land", R.mipmap.wenyigewu_aiyuezhicheng);
        list.add(info);
        return list;
    }

    private static DbManager dbManager;

    public static List<Movie> createMovies(int[] movieIcons, String[] movieNames) {
        List<Movie> movieInfos = new ArrayList<>();
        Movie movie;
        for (int i = 0; i < movieIcons.length; i++) {
            movie = new Movie();
            movie.setMovieName(movieNames[i]);
            movie.setMovieThumb(movieIcons[i]);
            movie.setMoviePreviewPath("android.resource://com.hejunlin.com.kupa.hotel/" + R.raw.zn);
            movieInfos.add(movie);
        }
        return movieInfos;
    }

    /**
     * 解析电影信息
     *
     * @param result
     * @param typeId
     */
    public static List<MovieInfo> resolveMovieInfo(String result, int typeId) {
        List<MovieInfo> movieInfoList = new ArrayList<>();
        try {
            JSONTokener parse = new JSONTokener(result);
            JSONObject object = (JSONObject) parse.nextValue();
            if (object.optString("res", "error").equals("ok")) {
                JSONObject info = object.optJSONObject("info");
                JSONArray list = info.optJSONArray("list");
                int size = list.length();
                for (int i = 0; i < size; i++) {
                    MovieInfo movieInfo = new MovieInfo();
                    JSONObject item = list.optJSONObject(i);
                    JSONObject singleInfo = item.optJSONObject("mKupaMovieSingleInfo");
                    //电影基本信息
                    movieInfo.setMovieId(singleInfo.optInt("movieId"));
                    movieInfo.setTypeId(typeId);
                    movieInfo.setProfile(singleInfo.optString("movieProfile"));
                    movieInfo.setDirector(singleInfo.optString("movieDirector"));
                    movieInfo.setPerformer(singleInfo.optString("moviePerformer"));
                    movieInfo.setShowTime(singleInfo.optLong("movieShowTime"));
                    movieInfo.setDuration(singleInfo.optInt("movieDuration"));
                    movieInfo.setKeyWord(singleInfo.optString("movieKeyWord"));
                    movieInfo.setGrade(singleInfo.optDouble("movieGrade"));
                    movieInfo.setAreaName(singleInfo.optString("movieAreaName"));
                    //电影名称
                    JSONObject singleMain = item.optJSONObject("mKupaMovieSingleMain");
                    movieInfo.setName(singleMain.optString("movieName"));
                    //海报预告片
                    JSONObject singleResource = item.optJSONObject("mKupaMovieSingleResource");
//                    movieInfo.setPhotoName(singleResource.optString("moviePictureWxTvUri"));
                    movieInfo.setPreviewUri(singleResource.optString("previewUri"));
                    movieInfo.setNativeUri(singleResource.optString("movieNativeUri"));
                    movieInfo.setOnlineUri(singleResource.optString("movieOnlineUri"));
                    movieInfo.setCloudUri(singleResource.optString("movieCloudUri"));

                    movieInfoList.add(movieInfo);
                }
                //保存电影信息
                saveMovies(movieInfoList, typeId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Utils.log("电影解析出差：" + e.getMessage());
        }
        return movieInfoList;
    }

    /**
     * 请求电影类别
     */
    public static void requestMovieTypes() {
        RequestParams params = new RequestParams(Contacts.API_MOVIE_TYPE);
        params.addParameter("terminalId", 2);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                MoviesUtils.resolveType(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析电影分类
     *
     * @param result 服务器返回的数据
     * @return 分类
     */
    public static void resolveType(String result) {
        List<MovieType> list = new ArrayList<>();
        try {
            JSONTokener parse = new JSONTokener(result);
            JSONObject object = (JSONObject) parse.nextValue();
            String res = object.optString("res", "error");
            if (res.equals("ok")) {
                JSONArray info = object.optJSONArray("info");
                int len = info.length();
                for (int i = 0; i < len; i++) {
                    JSONObject classify = info.optJSONObject(i);
                    JSONArray types = classify.optJSONArray("mKupaMovieClassifyInfo");
                    int length = types.length();
                    for (int j = 0; j < length; j++) {
                        JSONObject movieType = types.optJSONObject(j);
                        MovieType type = new MovieType(movieType.optInt("classifyId"), movieType.optString("classifyName"));
                        list.add(type);
                    }
                }
                //保存到数据库
                saveTypes(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过电影id查询电影信息
     *
     * @param movieId
     * @return
     */
    public static MovieInfo queryMovieById(int movieId) {
        try {
            getDbManager();
            return dbManager.selector(MovieInfo.class).where("movieId", "=", movieId).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过类型id查询电影
     *
     * @param typeId
     * @return
     */
    public static List<MovieInfo> queryMovieByTypeId(int typeId) {
        List<MovieInfo> list = new ArrayList<>();
        try {
            getDbManager();
            list = dbManager.selector(MovieInfo.class).where("typeId", "=", typeId).findAll();
            if (null != list)
                Utils.log("数据库中的数据：" + list.size());
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询数据库里的所有电影类别
     *
     * @return 电影类别
     */
    public static List<MovieType> queryTypes() {
        List<MovieType> list = new ArrayList<>();
        try {
            getDbManager();
            list = dbManager.findAll(MovieType.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 保存电影信息
     *
     * @param movies
     */
    private static void saveMovies(List<MovieInfo> movies, int typeId) {
        if (movies.size() > 0) {
            Utils.log("保存的电影数：" + movies.size());
            try {
                getDbManager();
                WhereBuilder builder = WhereBuilder.b();
                builder.and("typeId", "=", typeId);
                dbManager.delete(MovieInfo.class, builder);
                List<MovieInfo> list = dbManager.findAll(MovieInfo.class);
                Utils.log("保存的类型id：" + typeId);
                Utils.log("数据所以数据：" + list);
//                for (MovieInfo info : list)
//                    Utils.log("该类型还有值：" + info.getName() + "  id：" + info.getTypeId());
                dbManager.save(movies);
            } catch (DbException e) {
                Utils.log("保存失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存电影类型
     *
     * @param types 电影类型
     */
    private static void saveTypes(List<MovieType> types) {
        if (types.size() > 0) {
            try {
                getDbManager();
                dbManager.delete(MovieType.class);
                dbManager.save(types);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除所有电影信息
     */
    public static void deleteAllMovies() {
        try {
            getDbManager();
            dbManager.delete(MovieType.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取DbManager
     */
    private static void getDbManager() {
        dbManager = DatabaseUtil.getManager();
    }

    /**
     * 获取推送的电影id
     *
     * @param context
     * @return
     */
    public static int getPushMovieId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("kupaHotel", 0);
        return sp.getInt("movieId", -1);
    }

    /**
     * 保存推送的电影id
     *
     * @param context
     * @param id
     */
    public static void savePushMovieId(Context context, int id) {
        SharedPreferences sp = context.getSharedPreferences("kupaHotel", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("movieId", id).apply();
    }

    /**
     * 处理播放状态
     *
     * @param context
     * @param status
     */
    public static void dealPlayStatus(Context context, int status, long duration) {
        switch (status) {
            case Contacts.PLAY_STATUS_COMPLETE:
                pushPlayComplete(context);
                break;

            case Contacts.PLAY_STATUS_PAUSE:
            case Contacts.PLAY_STATUS_RESTART:
            case Contacts.PLAY_STATUS_START:
            case Contacts.PLAY_STATUS_ERROR:
                pushPlayTime(context, false, duration);
                break;

            case Contacts.PLAY_STATUS_BACK:
                pushPlayTime(context, true, duration);
                break;
        }
    }

    /**
     * 同步播放时间
     *
     * @param context
     */
    private static void pushPlayTime(Context context, boolean isComplete, long duration) {
        RequestParams params = new RequestParams(Contacts.API_SYNCHRONICE_PLAY_TIME);
        params.addParameter("isComplete", isComplete);
        params.addParameter("movieId", getPushMovieId(context));
        params.addParameter("roomId", Contacts.ROOMID);
        params.addParameter("duration", duration);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Utils.log("同步播放时间返回结果：" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Utils.log("同步播放时间失败：" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 推送播放完成
     *
     * @param context
     */
    private static void pushPlayComplete(Context context) {
        RequestParams params = new RequestParams(Contacts.API_PLAY_MOVIE_COMPLETE);
        params.addParameter("completeMovieId", getPushMovieId(context));
        params.addParameter("roomId", Contacts.ROOMID);
        params.addParameter("isNext", false);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Utils.log("推送电影播放完成返回结果：" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Utils.log("推送电影播放完成失败：" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
