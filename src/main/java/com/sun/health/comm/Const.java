package com.sun.health.comm;

public class Const {

    public static final String imagePath = "/Users/stonechen/tmp/report_image";

//    public static final String content = "{\"TextDetections\":[{\"DetectedText\":\"漳州市医院检验报告单\",\"Confidence\":99,\"Polygon\":[{\"X\":766,\"Y\":36},{\"X\":1103,\"Y\":36},{\"X\":1103,\"Y\":71},{\"X\":766,\"Y\":71}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":1}}\",\"ItemPolygon\":{\"X\":766,\"Y\":36,\"Width\":337,\"Height\":35},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"2798999000\",\"Confidence\":99,\"Polygon\":[{\"X\":1250,\"Y\":64},{\"X\":1320,\"Y\":64},{\"X\":1320,\"Y\":76},{\"X\":1250,\"Y\":76}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":2}}\",\"ItemPolygon\":{\"X\":1250,\"Y\":64,\"Width\":70,\"Height\":12},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"姓名:陈吉发\",\"Confidence\":99,\"Polygon\":[{\"X\":482,\"Y\":92},{\"X\":576,\"Y\":92},{\"X\":576,\"Y\":110},{\"X\":482,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":3}}\",\"ItemPolygon\":{\"X\":482,\"Y\":92,\"Width\":94,\"Height\":18},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"病历号:582524\",\"Confidence\":98,\"Polygon\":[{\"X\":673,\"Y\":94},{\"X\":798,\"Y\":94},{\"X\":798,\"Y\":110},{\"X\":673,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":4}}\",\"ItemPolygon\":{\"X\":673,\"Y\":94,\"Width\":125,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"样本编号:20211028G0082048\",\"Confidence\":99,\"Polygon\":[{\"X\":925,\"Y\":94},{\"X\":1123,\"Y\":94},{\"X\":1123,\"Y\":110},{\"X\":925,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":35}}\",\"ItemPolygon\":{\"X\":925,\"Y\":94,\"Width\":198,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"申请时间:2021-10-2811:07\",\"Confidence\":99,\"Polygon\":[{\"X\":1176,\"Y\":94},{\"X\":1375,\"Y\":94},{\"X\":1375,\"Y\":110},{\"X\":1176,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":36}}\",\"ItemPolygon\":{\"X\":1176,\"Y\":94,\"Width\":199,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"性别:男\",\"Confidence\":92,\"Polygon\":[{\"X\":482,\"Y\":120},{\"X\":549,\"Y\":120},{\"X\":549,\"Y\":133},{\"X\":482,\"Y\":133}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":5}}\",\"ItemPolygon\":{\"X\":482,\"Y\":120,\"Width\":67,\"Height\":13},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"送检医牛:蔡友鹏/0603\",\"Confidence\":99,\"Polygon\":[{\"X\":673,\"Y\":117},{\"X\":837,\"Y\":117},{\"X\":837,\"Y\":133},{\"X\":673,\"Y\":133}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":6}}\",\"ItemPolygon\":{\"X\":673,\"Y\":117,\"Width\":164,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"接收人员:肖美春\",\"Confidence\":97,\"Polygon\":[{\"X\":923,\"Y\":117},{\"X\":1050,\"Y\":120},{\"X\":1049,\"Y\":136},{\"X\":922,\"Y\":134}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":37}}\",\"ItemPolygon\":{\"X\":923,\"Y\":117,\"Width\":127,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"采集时间:2021-10-2811:24\",\"Confidence\":97,\"Polygon\":[{\"X\":1176,\"Y\":120},{\"X\":1377,\"Y\":120},{\"X\":1377,\"Y\":136},{\"X\":1176,\"Y\":136}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":38}}\",\"ItemPolygon\":{\"X\":1176,\"Y\":120,\"Width\":201,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"年龄:62岁\",\"Confidence\":99,\"Polygon\":[{\"X\":482,\"Y\":143},{\"X\":560,\"Y\":143},{\"X\":560,\"Y\":159},{\"X\":482,\"Y\":159}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":7}}\",\"ItemPolygon\":{\"X\":482,\"Y\":143,\"Width\":78,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"科别:肿瘤内科(二)\",\"Confidence\":98,\"Polygon\":[{\"X\":673,\"Y\":143},{\"X\":851,\"Y\":143},{\"X\":851,\"Y\":159},{\"X\":673,\"Y\":159}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":8}}\",\"ItemPolygon\":{\"X\":673,\"Y\":143,\"Width\":178,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"检验仪器:STAGC\",\"Confidence\":99,\"Polygon\":[{\"X\":923,\"Y\":143},{\"X\":1040,\"Y\":143},{\"X\":1040,\"Y\":159},{\"X\":923,\"Y\":159}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":39}}\",\"ItemPolygon\":{\"X\":923,\"Y\":143,\"Width\":117,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"接收时间:2021-10-2811:41\",\"Confidence\":98,\"Polygon\":[{\"X\":1176,\"Y\":143},{\"X\":1375,\"Y\":143},{\"X\":1375,\"Y\":159},{\"X\":1176,\"Y\":159}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":40}}\",\"ItemPolygon\":{\"X\":1176,\"Y\":143,\"Width\":199,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"床号:10\",\"Confidence\":95,\"Polygon\":[{\"X\":482,\"Y\":166},{\"X\":549,\"Y\":166},{\"X\":549,\"Y\":182},{\"X\":482,\"Y\":182}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":9}}\",\"ItemPolygon\":{\"X\":482,\"Y\":166,\"Width\":67,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"标本种类:血浆\",\"Confidence\":98,\"Polygon\":[{\"X\":673,\"Y\":168},{\"X\":784,\"Y\":168},{\"X\":784,\"Y\":184},{\"X\":673,\"Y\":184}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":10}}\",\"ItemPolygon\":{\"X\":673,\"Y\":168,\"Width\":111,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"检验目的:急诊凝血4项+血浆D2聚体\",\"Confidence\":98,\"Polygon\":[{\"X\":925,\"Y\":168},{\"X\":1170,\"Y\":168},{\"X\":1170,\"Y\":184},{\"X\":925,\"Y\":184}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":41}}\",\"ItemPolygon\":{\"X\":925,\"Y\":168,\"Width\":245,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"临床诊断:胰腺恶性肿瘤\",\"Confidence\":98,\"Polygon\":[{\"X\":482,\"Y\":191},{\"X\":646,\"Y\":191},{\"X\":646,\"Y\":207},{\"X\":482,\"Y\":207}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":11}}\",\"ItemPolygon\":{\"X\":482,\"Y\":191,\"Width\":164,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"备\",\"Confidence\":99,\"Polygon\":[{\"X\":923,\"Y\":191},{\"X\":941,\"Y\":191},{\"X\":941,\"Y\":207},{\"X\":923,\"Y\":207}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":42}}\",\"ItemPolygon\":{\"X\":923,\"Y\":191,\"Width\":18,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"注:\",\"Confidence\":99,\"Polygon\":[{\"X\":957,\"Y\":191},{\"X\":992,\"Y\":191},{\"X\":992,\"Y\":207},{\"X\":957,\"Y\":207}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":42}}\",\"ItemPolygon\":{\"X\":957,\"Y\":191,\"Width\":35,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"申请单据:264465781.264465782\",\"Confidence\":99,\"Polygon\":[{\"X\":482,\"Y\":216},{\"X\":701,\"Y\":216},{\"X\":701,\"Y\":230},{\"X\":482,\"Y\":230}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":12}}\",\"ItemPolygon\":{\"X\":482,\"Y\":216,\"Width\":219,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"NO\",\"Confidence\":94,\"Polygon\":[{\"X\":480,\"Y\":242},{\"X\":500,\"Y\":242},{\"X\":500,\"Y\":256},{\"X\":480,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":13}}\",\"ItemPolygon\":{\"X\":480,\"Y\":242,\"Width\":20,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"项\",\"Confidence\":99,\"Polygon\":[{\"X\":523,\"Y\":242},{\"X\":542,\"Y\":242},{\"X\":542,\"Y\":256},{\"X\":523,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":13}}\",\"ItemPolygon\":{\"X\":523,\"Y\":242,\"Width\":19,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"目\",\"Confidence\":99,\"Polygon\":[{\"X\":611,\"Y\":242},{\"X\":625,\"Y\":242},{\"X\":625,\"Y\":256},{\"X\":611,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":14}}\",\"ItemPolygon\":{\"X\":611,\"Y\":242,\"Width\":14,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"结果\",\"Confidence\":99,\"Polygon\":[{\"X\":833,\"Y\":242},{\"X\":865,\"Y\":242},{\"X\":865,\"Y\":256},{\"X\":833,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":15}}\",\"ItemPolygon\":{\"X\":833,\"Y\":242,\"Width\":32,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"参考范围\",\"Confidence\":99,\"Polygon\":[{\"X\":1036,\"Y\":242},{\"X\":1096,\"Y\":242},{\"X\":1096,\"Y\":258},{\"X\":1036,\"Y\":258}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":43}}\",\"ItemPolygon\":{\"X\":1036,\"Y\":242,\"Width\":60,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"单位\",\"Confidence\":99,\"Polygon\":[{\"X\":1213,\"Y\":242},{\"X\":1246,\"Y\":242},{\"X\":1246,\"Y\":256},{\"X\":1213,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":49}}\",\"ItemPolygon\":{\"X\":1213,\"Y\":242,\"Width\":33,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"检测方法\",\"Confidence\":99,\"Polygon\":[{\"X\":1313,\"Y\":242},{\"X\":1373,\"Y\":242},{\"X\":1373,\"Y\":258},{\"X\":1313,\"Y\":258}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":51}}\",\"ItemPolygon\":{\"X\":1313,\"Y\":242,\"Width\":60,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"凝血酶原时间(PT)\",\"Confidence\":99,\"Polygon\":[{\"X\":510,\"Y\":267},{\"X\":630,\"Y\":267},{\"X\":630,\"Y\":281},{\"X\":510,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":16}}\",\"ItemPolygon\":{\"X\":510,\"Y\":267,\"Width\":120,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"15.1\",\"Confidence\":97,\"Polygon\":[{\"X\":830,\"Y\":267},{\"X\":858,\"Y\":267},{\"X\":858,\"Y\":281},{\"X\":830,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":17}}\",\"ItemPolygon\":{\"X\":830,\"Y\":267,\"Width\":28,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"11-14.5\",\"Confidence\":99,\"Polygon\":[{\"X\":999,\"Y\":267},{\"X\":1054,\"Y\":267},{\"X\":1054,\"Y\":281},{\"X\":999,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":43}}\",\"ItemPolygon\":{\"X\":999,\"Y\":267,\"Width\":55,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"秒\",\"Confidence\":99,\"Polygon\":[{\"X\":1206,\"Y\":265},{\"X\":1223,\"Y\":265},{\"X\":1223,\"Y\":281},{\"X\":1206,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":50}}\",\"ItemPolygon\":{\"X\":1206,\"Y\":265,\"Width\":17,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"2凝血酶原时间国际单位(PT-INR)\",\"Confidence\":99,\"Polygon\":[{\"X\":480,\"Y\":288},{\"X\":722,\"Y\":288},{\"X\":722,\"Y\":304},{\"X\":480,\"Y\":304}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":18}}\",\"ItemPolygon\":{\"X\":480,\"Y\":288,\"Width\":242,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1.21\",\"Confidence\":99,\"Polygon\":[{\"X\":830,\"Y\":288},{\"X\":858,\"Y\":288},{\"X\":858,\"Y\":302},{\"X\":830,\"Y\":302}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":19}}\",\"ItemPolygon\":{\"X\":830,\"Y\":288,\"Width\":28,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0.8-1.2\",\"Confidence\":99,\"Polygon\":[{\"X\":999,\"Y\":288},{\"X\":1054,\"Y\":288},{\"X\":1054,\"Y\":302},{\"X\":999,\"Y\":302}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":44}}\",\"ItemPolygon\":{\"X\":999,\"Y\":288,\"Width\":55,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"凝血酶原时间比率(PT-R)\",\"Confidence\":99,\"Polygon\":[{\"X\":510,\"Y\":309},{\"X\":676,\"Y\":309},{\"X\":676,\"Y\":325},{\"X\":510,\"Y\":325}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":20}}\",\"ItemPolygon\":{\"X\":510,\"Y\":309,\"Width\":166,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1.16\",\"Confidence\":99,\"Polygon\":[{\"X\":830,\"Y\":309},{\"X\":860,\"Y\":309},{\"X\":860,\"Y\":323},{\"X\":830,\"Y\":323}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":21}}\",\"ItemPolygon\":{\"X\":830,\"Y\":309,\"Width\":30,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0.8-1.2\",\"Confidence\":99,\"Polygon\":[{\"X\":999,\"Y\":309},{\"X\":1054,\"Y\":309},{\"X\":1054,\"Y\":323},{\"X\":999,\"Y\":323}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":44}}\",\"ItemPolygon\":{\"X\":999,\"Y\":309,\"Width\":55,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"活化部分凝血活酶时间(APTT)\",\"Confidence\":99,\"Polygon\":[{\"X\":510,\"Y\":332},{\"X\":706,\"Y\":332},{\"X\":706,\"Y\":346},{\"X\":510,\"Y\":346}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":22}}\",\"ItemPolygon\":{\"X\":510,\"Y\":332,\"Width\":196,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"41.9\",\"Confidence\":99,\"Polygon\":[{\"X\":828,\"Y\":332},{\"X\":858,\"Y\":332},{\"X\":858,\"Y\":346},{\"X\":828,\"Y\":346}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":23}}\",\"ItemPolygon\":{\"X\":828,\"Y\":332,\"Width\":30,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"26-42\",\"Confidence\":99,\"Polygon\":[{\"X\":999,\"Y\":332},{\"X\":1038,\"Y\":332},{\"X\":1038,\"Y\":346},{\"X\":999,\"Y\":346}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":45}}\",\"ItemPolygon\":{\"X\":999,\"Y\":332,\"Width\":39,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"秒\",\"Confidence\":99,\"Polygon\":[{\"X\":1206,\"Y\":330},{\"X\":1223,\"Y\":330},{\"X\":1223,\"Y\":346},{\"X\":1206,\"Y\":346}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":52}}\",\"ItemPolygon\":{\"X\":1206,\"Y\":330,\"Width\":17,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"6\",\"Confidence\":51,\"Polygon\":[{\"X\":480,\"Y\":355},{\"X\":489,\"Y\":355},{\"X\":489,\"Y\":366},{\"X\":480,\"Y\":366}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":24}}\",\"ItemPolygon\":{\"X\":480,\"Y\":355,\"Width\":9,\"Height\":11},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"活化凝血活酶时间比率(APTT-R)\",\"Confidence\":98,\"Polygon\":[{\"X\":512,\"Y\":353},{\"X\":720,\"Y\":353},{\"X\":720,\"Y\":369},{\"X\":512,\"Y\":369}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":25}}\",\"ItemPolygon\":{\"X\":512,\"Y\":353,\"Width\":208,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1.23\",\"Confidence\":91,\"Polygon\":[{\"X\":830,\"Y\":353},{\"X\":860,\"Y\":353},{\"X\":860,\"Y\":366},{\"X\":830,\"Y\":366}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":26}}\",\"ItemPolygon\":{\"X\":830,\"Y\":353,\"Width\":30,\"Height\":13},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0.76-1.24\",\"Confidence\":95,\"Polygon\":[{\"X\":999,\"Y\":353},{\"X\":1068,\"Y\":353},{\"X\":1068,\"Y\":369},{\"X\":999,\"Y\":369}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":46}}\",\"ItemPolygon\":{\"X\":999,\"Y\":353,\"Width\":69,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"凝血酶时间(TT\",\"Confidence\":99,\"Polygon\":[{\"X\":510,\"Y\":373},{\"X\":613,\"Y\":373},{\"X\":613,\"Y\":390},{\"X\":510,\"Y\":390}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":27}}\",\"ItemPolygon\":{\"X\":510,\"Y\":373,\"Width\":103,\"Height\":17},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"14.3\",\"Confidence\":98,\"Polygon\":[{\"X\":830,\"Y\":376},{\"X\":860,\"Y\":376},{\"X\":860,\"Y\":387},{\"X\":830,\"Y\":387}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":28}}\",\"ItemPolygon\":{\"X\":830,\"Y\":376,\"Width\":30,\"Height\":11},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"14-21\",\"Confidence\":99,\"Polygon\":[{\"X\":1001,\"Y\":376},{\"X\":1036,\"Y\":376},{\"X\":1036,\"Y\":387},{\"X\":1001,\"Y\":387}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":46}}\",\"ItemPolygon\":{\"X\":1001,\"Y\":376,\"Width\":35,\"Height\":11},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"秒\",\"Confidence\":99,\"Polygon\":[{\"X\":1206,\"Y\":373},{\"X\":1223,\"Y\":373},{\"X\":1223,\"Y\":390},{\"X\":1206,\"Y\":390}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":53}}\",\"ItemPolygon\":{\"X\":1206,\"Y\":373,\"Width\":17,\"Height\":17},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"7凝血酶时间比率(TT-R)\",\"Confidence\":99,\"Polygon\":[{\"X\":480,\"Y\":396},{\"X\":660,\"Y\":396},{\"X\":660,\"Y\":410},{\"X\":480,\"Y\":410}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":29}}\",\"ItemPolygon\":{\"X\":480,\"Y\":396,\"Width\":180,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0.84\",\"Confidence\":99,\"Polygon\":[{\"X\":830,\"Y\":396},{\"X\":860,\"Y\":396},{\"X\":860,\"Y\":410},{\"X\":830,\"Y\":410}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":30}}\",\"ItemPolygon\":{\"X\":830,\"Y\":396,\"Width\":30,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0.82-1.24\",\"Confidence\":99,\"Polygon\":[{\"X\":999,\"Y\":396},{\"X\":1068,\"Y\":396},{\"X\":1068,\"Y\":410},{\"X\":999,\"Y\":410}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":47}}\",\"ItemPolygon\":{\"X\":999,\"Y\":396,\"Width\":69,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"8\",\"Confidence\":98,\"Polygon\":[{\"X\":480,\"Y\":420},{\"X\":491,\"Y\":420},{\"X\":491,\"Y\":431},{\"X\":480,\"Y\":431}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":31}}\",\"ItemPolygon\":{\"X\":480,\"Y\":420,\"Width\":11,\"Height\":11},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"纤维蛋白原(FIB-C)\",\"Confidence\":96,\"Polygon\":[{\"X\":507,\"Y\":417},{\"X\":639,\"Y\":417},{\"X\":639,\"Y\":433},{\"X\":507,\"Y\":433}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":31}}\",\"ItemPolygon\":{\"X\":507,\"Y\":417,\"Width\":132,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"7.62\",\"Confidence\":99,\"Polygon\":[{\"X\":828,\"Y\":417},{\"X\":860,\"Y\":417},{\"X\":860,\"Y\":433},{\"X\":828,\"Y\":433}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":32}}\",\"ItemPolygon\":{\"X\":828,\"Y\":417,\"Width\":32,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"2-4\",\"Confidence\":96,\"Polygon\":[{\"X\":999,\"Y\":417},{\"X\":1022,\"Y\":417},{\"X\":1022,\"Y\":431},{\"X\":999,\"Y\":431}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":47}}\",\"ItemPolygon\":{\"X\":999,\"Y\":417,\"Width\":23,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"g/L\",\"Confidence\":92,\"Polygon\":[{\"X\":1205,\"Y\":420},{\"X\":1228,\"Y\":416},{\"X\":1230,\"Y\":431},{\"X\":1207,\"Y\":435}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":54}}\",\"ItemPolygon\":{\"X\":1205,\"Y\":420,\"Width\":23,\"Height\":15},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"D二聚体(DD)\",\"Confidence\":99,\"Polygon\":[{\"X\":510,\"Y\":438},{\"X\":593,\"Y\":438},{\"X\":593,\"Y\":454},{\"X\":510,\"Y\":454}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":33}}\",\"ItemPolygon\":{\"X\":510,\"Y\":438,\"Width\":83,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1926.00\",\"Confidence\":97,\"Polygon\":[{\"X\":830,\"Y\":440},{\"X\":881,\"Y\":440},{\"X\":881,\"Y\":454},{\"X\":830,\"Y\":454}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":34}}\",\"ItemPolygon\":{\"X\":830,\"Y\":440,\"Width\":51,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0-500\",\"Confidence\":99,\"Polygon\":[{\"X\":999,\"Y\":436},{\"X\":1039,\"Y\":439},{\"X\":1038,\"Y\":455},{\"X\":998,\"Y\":452}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":48}}\",\"ItemPolygon\":{\"X\":999,\"Y\":436,\"Width\":40,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"ng/mL\",\"Confidence\":99,\"Polygon\":[{\"X\":1206,\"Y\":440},{\"X\":1246,\"Y\":440},{\"X\":1246,\"Y\":454},{\"X\":1206,\"Y\":454}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":55}}\",\"ItemPolygon\":{\"X\":1206,\"Y\":440,\"Width\":40,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]}],\"Language\":\"zh\",\"Angel\":359.99,\"PdfPageSize\":0,\"RequestId\":\"b6c474ff-9115-4bc1-989e-90fc2c377a28\"}\n";

    public static final String content = "{\"TextDetections\":[{\"DetectedText\":\"漳州市医院检验报告单\",\"Confidence\":99,\"Polygon\":[{\"X\":768,\"Y\":36},{\"X\":1103,\"Y\":36},{\"X\":1103,\"Y\":71},{\"X\":768,\"Y\":71}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":1}}\",\"ItemPolygon\":{\"X\":768,\"Y\":36,\"Width\":335,\"Height\":35},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"2807721900\",\"Confidence\":99,\"Polygon\":[{\"X\":1248,\"Y\":63},{\"X\":1322,\"Y\":67},{\"X\":1322,\"Y\":80},{\"X\":1248,\"Y\":77}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":16}}\",\"ItemPolygon\":{\"X\":1248,\"Y\":63,\"Width\":74,\"Height\":13},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"姓名:陈吉发\",\"Confidence\":99,\"Polygon\":[{\"X\":482,\"Y\":92},{\"X\":576,\"Y\":92},{\"X\":576,\"Y\":110},{\"X\":482,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":2}}\",\"ItemPolygon\":{\"X\":482,\"Y\":92,\"Width\":94,\"Height\":18},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"病历号:582524\",\"Confidence\":98,\"Polygon\":[{\"X\":673,\"Y\":94},{\"X\":798,\"Y\":94},{\"X\":798,\"Y\":110},{\"X\":673,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":9}}\",\"ItemPolygon\":{\"X\":673,\"Y\":94,\"Width\":125,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"样本编号:20211104G0422068\",\"Confidence\":99,\"Polygon\":[{\"X\":925,\"Y\":94},{\"X\":1123,\"Y\":94},{\"X\":1123,\"Y\":110},{\"X\":925,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":13}}\",\"ItemPolygon\":{\"X\":925,\"Y\":94,\"Width\":198,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"申请时间:2021-11-0311:51\",\"Confidence\":99,\"Polygon\":[{\"X\":1176,\"Y\":94},{\"X\":1375,\"Y\":94},{\"X\":1375,\"Y\":110},{\"X\":1176,\"Y\":110}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":17}}\",\"ItemPolygon\":{\"X\":1176,\"Y\":94,\"Width\":199,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"性别:男\",\"Confidence\":93,\"Polygon\":[{\"X\":482,\"Y\":120},{\"X\":549,\"Y\":120},{\"X\":549,\"Y\":133},{\"X\":482,\"Y\":133}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":3}}\",\"ItemPolygon\":{\"X\":482,\"Y\":120,\"Width\":67,\"Height\":13},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"送检医牛:蔡友鹏/0603\",\"Confidence\":99,\"Polygon\":[{\"X\":673,\"Y\":117},{\"X\":837,\"Y\":117},{\"X\":837,\"Y\":133},{\"X\":673,\"Y\":133}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":10}}\",\"ItemPolygon\":{\"X\":673,\"Y\":117,\"Width\":164,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"接收人员:林冬梅\",\"Confidence\":96,\"Polygon\":[{\"X\":925,\"Y\":117},{\"X\":1047,\"Y\":117},{\"X\":1047,\"Y\":133},{\"X\":925,\"Y\":133}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":13}}\",\"ItemPolygon\":{\"X\":925,\"Y\":117,\"Width\":122,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"采集时间:2021-11-0406:20\",\"Confidence\":98,\"Polygon\":[{\"X\":1176,\"Y\":120},{\"X\":1377,\"Y\":120},{\"X\":1377,\"Y\":133},{\"X\":1176,\"Y\":133}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":17}}\",\"ItemPolygon\":{\"X\":1176,\"Y\":120,\"Width\":201,\"Height\":13},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"年龄:62岁\",\"Confidence\":99,\"Polygon\":[{\"X\":482,\"Y\":143},{\"X\":560,\"Y\":143},{\"X\":560,\"Y\":159},{\"X\":482,\"Y\":159}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":4}}\",\"ItemPolygon\":{\"X\":482,\"Y\":143,\"Width\":78,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"科别:肿瘤内科(二)\",\"Confidence\":97,\"Polygon\":[{\"X\":673,\"Y\":143},{\"X\":851,\"Y\":143},{\"X\":851,\"Y\":159},{\"X\":673,\"Y\":159}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":10}}\",\"ItemPolygon\":{\"X\":673,\"Y\":143,\"Width\":178,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"检验仪器:ADVLACENTAUR\",\"Confidence\":99,\"Polygon\":[{\"X\":923,\"Y\":143},{\"X\":1093,\"Y\":143},{\"X\":1093,\"Y\":159},{\"X\":923,\"Y\":159}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":14}}\",\"ItemPolygon\":{\"X\":923,\"Y\":143,\"Width\":170,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"接收时间:2021-11-04 07:56\",\"Confidence\":98,\"Polygon\":[{\"X\":1174,\"Y\":143},{\"X\":1377,\"Y\":143},{\"X\":1377,\"Y\":159},{\"X\":1174,\"Y\":159}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":17}}\",\"ItemPolygon\":{\"X\":1174,\"Y\":143,\"Width\":203,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"床号:10\",\"Confidence\":94,\"Polygon\":[{\"X\":482,\"Y\":166},{\"X\":549,\"Y\":166},{\"X\":549,\"Y\":182},{\"X\":482,\"Y\":182}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":5}}\",\"ItemPolygon\":{\"X\":482,\"Y\":166,\"Width\":67,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"标本种类:血清\",\"Confidence\":98,\"Polygon\":[{\"X\":673,\"Y\":168},{\"X\":784,\"Y\":168},{\"X\":784,\"Y\":184},{\"X\":673,\"Y\":184}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":10}}\",\"ItemPolygon\":{\"X\":673,\"Y\":168,\"Width\":111,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"检验目的:肌钙蛋白I\",\"Confidence\":94,\"Polygon\":[{\"X\":925,\"Y\":168},{\"X\":1075,\"Y\":168},{\"X\":1075,\"Y\":184},{\"X\":925,\"Y\":184}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":14}}\",\"ItemPolygon\":{\"X\":925,\"Y\":168,\"Width\":150,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"临床诊断:胰腺恶性肿瘤\",\"Confidence\":98,\"Polygon\":[{\"X\":482,\"Y\":191},{\"X\":646,\"Y\":191},{\"X\":646,\"Y\":207},{\"X\":482,\"Y\":207}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":6}}\",\"ItemPolygon\":{\"X\":482,\"Y\":191,\"Width\":164,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"备\",\"Confidence\":99,\"Polygon\":[{\"X\":923,\"Y\":191},{\"X\":941,\"Y\":191},{\"X\":941,\"Y\":207},{\"X\":923,\"Y\":207}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":14}}\",\"ItemPolygon\":{\"X\":923,\"Y\":191,\"Width\":18,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"注:\",\"Confidence\":99,\"Polygon\":[{\"X\":950,\"Y\":191},{\"X\":992,\"Y\":191},{\"X\":992,\"Y\":207},{\"X\":950,\"Y\":207}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":14}}\",\"ItemPolygon\":{\"X\":950,\"Y\":191,\"Width\":42,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"申请单据:265079744\",\"Confidence\":99,\"Polygon\":[{\"X\":482,\"Y\":216},{\"X\":623,\"Y\":216},{\"X\":623,\"Y\":230},{\"X\":482,\"Y\":230}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":6}}\",\"ItemPolygon\":{\"X\":482,\"Y\":216,\"Width\":141,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"NO\",\"Confidence\":94,\"Polygon\":[{\"X\":480,\"Y\":242},{\"X\":500,\"Y\":242},{\"X\":500,\"Y\":256},{\"X\":480,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":6}}\",\"ItemPolygon\":{\"X\":480,\"Y\":242,\"Width\":20,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"项\",\"Confidence\":99,\"Polygon\":[{\"X\":521,\"Y\":242},{\"X\":542,\"Y\":242},{\"X\":542,\"Y\":256},{\"X\":521,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":6}}\",\"ItemPolygon\":{\"X\":521,\"Y\":242,\"Width\":21,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"目\",\"Confidence\":95,\"Polygon\":[{\"X\":611,\"Y\":242},{\"X\":623,\"Y\":242},{\"X\":623,\"Y\":256},{\"X\":611,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":7}}\",\"ItemPolygon\":{\"X\":611,\"Y\":242,\"Width\":12,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"结果\",\"Confidence\":99,\"Polygon\":[{\"X\":833,\"Y\":242},{\"X\":865,\"Y\":242},{\"X\":865,\"Y\":256},{\"X\":833,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":11}}\",\"ItemPolygon\":{\"X\":833,\"Y\":242,\"Width\":32,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"参考范围\",\"Confidence\":99,\"Polygon\":[{\"X\":1036,\"Y\":242},{\"X\":1096,\"Y\":242},{\"X\":1096,\"Y\":256},{\"X\":1036,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":15}}\",\"ItemPolygon\":{\"X\":1036,\"Y\":242,\"Width\":60,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"单位\",\"Confidence\":99,\"Polygon\":[{\"X\":1213,\"Y\":242},{\"X\":1243,\"Y\":242},{\"X\":1243,\"Y\":256},{\"X\":1213,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":18}}\",\"ItemPolygon\":{\"X\":1213,\"Y\":242,\"Width\":30,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"检测方法\",\"Confidence\":96,\"Polygon\":[{\"X\":1313,\"Y\":242},{\"X\":1373,\"Y\":242},{\"X\":1373,\"Y\":256},{\"X\":1313,\"Y\":256}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":20}}\",\"ItemPolygon\":{\"X\":1313,\"Y\":242,\"Width\":60,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"1\",\"Confidence\":97,\"Polygon\":[{\"X\":480,\"Y\":267},{\"X\":493,\"Y\":267},{\"X\":493,\"Y\":281},{\"X\":480,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":8}}\",\"ItemPolygon\":{\"X\":480,\"Y\":267,\"Width\":13,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"肌钙蛋白I(CTNI)\",\"Confidence\":95,\"Polygon\":[{\"X\":507,\"Y\":265},{\"X\":623,\"Y\":265},{\"X\":623,\"Y\":281},{\"X\":507,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":8}}\",\"ItemPolygon\":{\"X\":507,\"Y\":265,\"Width\":116,\"Height\":16},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0.000\",\"Confidence\":99,\"Polygon\":[{\"X\":828,\"Y\":267},{\"X\":867,\"Y\":267},{\"X\":867,\"Y\":281},{\"X\":828,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":12}}\",\"ItemPolygon\":{\"X\":828,\"Y\":267,\"Width\":39,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"0.00-0.06\",\"Confidence\":99,\"Polygon\":[{\"X\":999,\"Y\":267},{\"X\":1068,\"Y\":267},{\"X\":1068,\"Y\":281},{\"X\":999,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":15}}\",\"ItemPolygon\":{\"X\":999,\"Y\":267,\"Width\":69,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"ng/ml\",\"Confidence\":97,\"Polygon\":[{\"X\":1206,\"Y\":267},{\"X\":1243,\"Y\":267},{\"X\":1243,\"Y\":281},{\"X\":1206,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":19}}\",\"ItemPolygon\":{\"X\":1206,\"Y\":267,\"Width\":37,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]},{\"DetectedText\":\"免疫荧光定量法\",\"Confidence\":99,\"Polygon\":[{\"X\":1276,\"Y\":267},{\"X\":1370,\"Y\":267},{\"X\":1370,\"Y\":281},{\"X\":1276,\"Y\":281}],\"AdvancedInfo\":\"{\\\"Parag\\\":{\\\"ParagNo\\\":20}}\",\"ItemPolygon\":{\"X\":1276,\"Y\":267,\"Width\":94,\"Height\":14},\"Words\":[],\"WordCoordPoint\":[]}],\"Language\":\"zh\",\"Angel\":359.99,\"PdfPageSize\":0,\"RequestId\":\"a68971bb-2161-4c6e-896c-13e5fa4b8cfb\"}\n";

}
