package com.kupa.hotel.utils;

import com.kupa.hotel.R;
import com.kupa.hotel.entity.Recommend;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mita on 2017/6/13.
 */

public class RecommendUtil {

    public static List<Recommend> getRecommend() {
        List<Recommend> list = new ArrayList<>();
        Recommend recommend = new Recommend(0,
                Contacts.RECOMMEND_CATE,
                "Food",
                "The five-colored sticky rice",
                "Wu-colored glutinous rice is a traditional flavor snack in buyi nationality and zhuang nationality area.Because of sticky rice were black, red, yellow, white, purple named five kinds of color, also known as \"rice\", lunar calendar in March third or qingming festival season, people of all ethnic groups in guangxi is generally made, five-colored sticky rice strong family loves, five-colored sticky rice luck, see it as a symbol of good harvest.The five colors of glutinous rice are colorful and attractive.Natural pigment is good for the human body, each has its fragrance and flavor.Wu-colored glutinous rice, fragrant, delicious, and tonic, fitness, medical, beauty and other functions.",
                R.mipmap.food_nuomifan);
        list.add(recommend);
//        recommend = new Recommend(Contacts.RECOMMEND_CATE,
//                Contacts.RECOMMEND_CATE,
//                "美食",
//                "傣族竹筒饭",
//                "云南傣族竹筒饭是融糥米香青竹香于一体,是色香味俱佳最具民族特色的风味食品,是云南民族美食中比较有代表性的特色美食,主要流行于西双版纳州、德宏州、普洱地区、临沧地区等等。",
//                R.mipmap.recomm_food_xiangzhufan);
//        list.add(recommend);
//
//        recommend = new Recommend(Contacts.RECOMMEND_CATE,
//                Contacts.RECOMMEND_CATE,
//                "美食",
//                "水煮肉片",
//                "水煮肉片肉味香辣软嫩易嚼,吃时肉嫩菜鲜汤红油亮麻辣味浓最宜下饭为家常美食之一。特色是麻辣鲜香。",
//                R.mipmap.food_shuizhuroupian);
//        list.add(recommend);

        recommend = new Recommend(1,
                Contacts.RECOMMEND_MOVIE,
                "Movie",
                "Angry Birds",
                "A group of flightless birds, huddled in a tropical island, peace and tranquility in the game a few classic image became the protagonist of the film, respectively is angry birds bright red Jason Sue dyche dubbing, speed the bird chuck josh gade dubbing, bomb Danny McBride, dubbing, when landing mysterious green pig island",
                R.mipmap.recommend_movie_fennudexiaoniao);
        list.add(recommend);

        recommend = new Recommend(2,
                Contacts.RECOMMEND_MOVIE,
                "Movie",
                "Resident Evil",
                "Alice was in danger when she was betrayed by westcott in Washington, d.c., and the human race almost lost hope.As the only survivor, also is the human last line of defence against zombie army, Alice must go back to a nightmare start place of raccoon city, redeem human justice mission to save the world.The return to the story of the goddess, raccoon, and former friends against zombies and the latest variety of monsters, has launched a thrilling final showdown with the umbrella company.",
                R.mipmap.recommend_movie_shenghuaweiji);
        list.add(recommend);

        recommend = new Recommend(3,
                Contacts.RECOMMEND_MOVIE,
                "Movie",
                "Big Hero 6",
                "Film story focuses on a robot to master infant prodigy hom and profound friendship between pneumatic robot to light, in the city they inadvertently found himself living in San Francisco crime after the crisis, hom to the good friend must be combined to light \"super disease express\" royal elder sister, god \"fly knife kitchen god\" mustard boundless, \"chemical witch\" HaNi lemon and otaku \"monster\" Fred for help.He decided to solve the mystery and save the city, so he transformed the team that never beat a crime into a high-tech hero, \"super marines\".",
                R.mipmap.recommend_movie_chaonengluzhandui);
        list.add(recommend);

        recommend = new Recommend(4,
                Contacts.RECOMMEND_TOUR,
                "View",
                "Blue moon valley",
                "Blue moon valley, its predecessor was earlier known as the white river, in the sunny days, the color of the water is blue, and the valley is crescent, far look like a blue moon set in at the foot of jade dragon snow mountain, so called blue in shadowmoon valley.The name of the white river is because the mud at the bottom of the lake is white. When it rains, the water turns white, so it's called the white river.",
                R.mipmap.recommend_view_lanyuegu);
        list.add(recommend);

        recommend = new Recommend(5,
                Contacts.RECOMMEND_TOUR,
                "View",
                "Jade Dragon Snow Mountain",
                "The scenery of lijiang, yulong snow mountain since ancient times is a magnificent snow-capped mountains, the tang dynasty nanzhao kingdom seeks for age, nanzhao state under different seeks to find yue worship the mountain, yulong snow mountain, has a gift for beiyue, baisha village north north since YueMiao remaining, still deep courtyard, cracking cliff face.Worshippers are on the road.Main products must stand the highest quality, climbing the highest mountain, visit lijiang, yulong snow mountain is a must choose project.",
                R.mipmap.recommend_view_yulongxueshan);
        list.add(recommend);

        recommend = new Recommend(6,
                Contacts.RECOMMEND_TOUR,
                "View",
                "Lugu Lake",
                "Lugu lake belong to the upper Yangtze river Yangtze river tributary of yalong river tributaries litang river system, rivers into the lake lugu lake, a total of 18, part of article 11 of yunnan, sichuan section 7, the lake by the river on the east side of the great grass sea injection in yanyuan ago, injected GaiZu river, downstream YongNingHe) and injected the wolong river (also known as lie down river, YanYuan river), the inflow of litang river, finally into the Yangtze river section of jinsha river tributaries upstream yalong river.",
                R.mipmap.recommend_view_luguhu);
        list.add(recommend);
        return list;
    }

    private static DbManager dbManager;

    /**
     * 保存推荐信息
     *
     * @param recommends 推荐信息
     */
    public static void saveRecommend(List<Recommend> recommends) {
        try {
            getDbManager();
            dbManager.delete(Recommend.class);
            dbManager.save(recommends);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据库所有的推荐信息
     *
     * @return List<Recommend>
     */
    public static List<Recommend> queryRecommend() {
        List<Recommend> list = new ArrayList<>();
        try {
            getDbManager();
            list = dbManager.findAll(Recommend.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获取DbManager
     */
    private static void getDbManager() {
        dbManager = DatabaseUtil.getManager();
    }
}
