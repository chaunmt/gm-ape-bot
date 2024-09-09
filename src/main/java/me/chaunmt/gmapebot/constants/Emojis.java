package me.chaunmt.gmapebot.constants;

import me.chaunmt.gmapebot.utils.RandomGenerator;

public class Emojis {
    public static final String[] FUN =
            {
                    "<:oneesama_bleh:1110748595294056479>",
                    "<:Neesama_uwaauwaa:1181005202686480484>",
                    "<:Venti_yey:1181009539009630331>",
                    "<:Kokomi_think:1181011128076214303>",
                    "<:Lynette_spark:1181006569903759431>",
                    "<:hutao_love:1007604545213444226>",
                    "<:hutao_wink:1007606429970071624>",
                    "<:hutao_greetings:1007813726298193930>",
                    "<:Faruzan_smort:1181010143735980136>",
                    "<:doggo_kek:1007615513217736775>",
                    "<:tanuki_rolling:1007556411909230612>",
                    "<:jean_praise:1028892495444185200>",
                    "<:ganyu_pray:1007813723194404924>",
                    "<:ganyu_smile:1007617852687253524>"
            };

    public  static String getRandomFunEmoji() {
        return FUN[RandomGenerator.getRandomInt(FUN.length)];
    }

    public static final String[] SAD =
            {
                    "<:amber_saveme:1007813718479994941>",
                    "<:March_7th_cry:1181005498779181147>",
                    "<:kazoo_cri:1103271610162622534>",
                    "<:Alhai_ughh:1181008931829596171>",
                    "<:beidou_bye:1007813720128364625>",
                    "<:ganyu_stare:1007605047577804900>",
                    "<:layla_cri:1075322926670295070>",
                    "<:keqing_mad:1007813002805919765>",
                    "<:klee_shame:1007650909406380052>",
                    "<:pain:1007616053939028048>",
                    "<:qiqi_deadinside:1007643996329345055>",
                    "<:xiao_deadinside:1007609464758743204>"
            };

    public  static String getRandomSadEmoji() {
        return SAD[RandomGenerator.getRandomInt(SAD.length)];
    }

    public static final String[][] CODE_HAND =      // Code in Discord and Unicode
            {
                    {
                            ":thumbsup:",
                            "U+1F44D"
                    },
                    {
                            ":thumbsdown:",
                            "U+1F44E"
                    }
            };

    public static final String[][] CODE_NUMBER =    // Code in Discord and Unicode
            {
                    {
                            ":one:",
                            "U+0031 U+20E3"
                    },
                    {
                            ":two:",
                            "U+0032 U+20E3"
                    },
                    {
                            ":three:",
                            "U+0033 U+20E3"
                    },
                    {
                            ":four:",
                            "U+0034 U+20E3"
                    },
                    {
                            ":five:",
                            "U+0035 U+20E3"
                    },
                    {
                            ":six:",
                            "U+0036 U+20E3"
                    },
                    {
                            ":seven:",
                            "U+0037 U+20E3"
                    },
                    {
                            ":eight:",
                            "U+0038 U+20E3"
                    },
                    {
                            ":nine:",
                            "U+0039 U+20E3"
                    },
                    {
                            ":keycap_ten:",
                            "U+1F51F"
                    }
            };
}