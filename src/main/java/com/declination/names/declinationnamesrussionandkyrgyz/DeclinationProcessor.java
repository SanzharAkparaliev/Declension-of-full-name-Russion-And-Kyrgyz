package com.declination.names.declinationnamesrussionandkyrgyz;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeclinationProcessor {
    static String sexM = "m";
    static String sexF = "f";
    // именительный
    static String gcaseIm =   "nominative";
    static String gcaseNom = "nominative";
    // родительный
    static String gcaseRod =  "genitive";
    static String gcaseGen = "genitive";
    // дательный
    static String gcaseDat =  "dative";
    // винительный
    static String gcaseVin =  "accusative";
    static String gcaseAcc = "accusative";
    // творительный
    static String gcaseTvor = "instrumentative";
    static String gcaseIns = "instrumentative";
    // предложный
    static String gcasePred = "prepositional";
    static String gcasePos = "prepositional";

    private DeclinationProcessor() {
    }

    static Map<String, Map<String, List<Object>>> rules = new HashMap<String,
            Map<String, List<Object>>>() {{
        put("lastName", new HashMap<String, List<Object>>() {{
            put("exceptions", new ArrayList<Object>() {{
                add("	дюма,тома,дега,люка,ферма,гамарра,петипа,шандра . . . . .");
                add("	гусь,ремень,камень,онук,богода,нечипас,долгопалец,маненок,рева,кива . . . . .");
                add("	вий,сой,цой,хой -я -ю -я -ем -е");
            }});
            put("suffixes", new ArrayList<Object>() {{
                add("f	б,в,г,д,ж,з,й,к,л,м,н,п,р,с,т,ф,х,ц,ч,ш,щ,ъ,ь . . . . .");
                add("f	ска,цка  -ой -ой -ую -ой -ой");
                add("f	ая       --ой --ой --ую --ой --ой");
                add("	ская     --ой --ой --ую --ой --ой");
                add("f	на       -ой -ой -у -ой -ой");

                add("	иной -я -ю -я -ем -е");
                add("	уй   -я -ю -я -ем -е");
                add("	ца   -ы -е -у -ей -е");

                add("	рих  а у а ом е");

                add("	ия                      . . . . .");
                add("	иа,аа,оа,уа,ыа,еа,юа,эа . . . . .");
                add("	их,ых                   . . . . .");
                add("	о,е,э,и,ы,у,ю           . . . . .");

                add("	ова,ева,ёва            -ой -ой -у -ой -ой");
                add("	га,ка,ха,ча,ща,жа  -и -е -у -ой -е");
                add("	ца  -и -е -у -ей -е");
                add("	а   -ы -е -у -ой -е");

                add("	ь   -я -ю -я -ем -е");

                add("	ия  -и -и -ю -ей -и");
                add("	я   -и -е -ю -ей -е");
                add("	ей  -я -ю -я -ем -е");

                add("	ян,ан,йн   а у а ом е");

                add("	ынец,обец  --ца --цу --ца --цем --це");
                add("	онец,овец  --ца --цу --ца --цом --це");

                add("	ц,ч,ш,щ   а у а ем е");

                add("	ай  -я -ю -я -ем -е");
                add("	гой,кой  -го -му -го --им -м");
                add("	ой  -го -му -го --ым -м");
                add("	ах,ив   а у а ом е");

                add("	ший,щий,жий,ний  --его --ему --его -м --ем");
                add("	кий,ый   --ого --ому --ого -м --ом");
                add("	ий       -я -ю -я -ем -и");

                add("	ок  --ка --ку --ка --ком --ке");
                add("	ец  --ца --цу --ца --цом --це");

                add("	в,н   а у а ым е");
                add("	б,г,д,ж,з,к,л,м,п,р,с,т,ф,х   а у а ом е");
            }});
        }});
        put("firstName", new HashMap<String, List<Object>>() {{
            put("exceptions", new ArrayList<Object>() {{
                add("	лев    --ьва --ьву --ьва --ьвом --ьве");
                add("	павел  --ла  --лу  --ла  --лом  --ле");
                add("m	шота   . . . . .");
                add("m	пётр   ---етра ---етру ---етра ---етром ---етре");
                add("f	рашель,нинель,николь,габриэль,даниэль   . . . . .");
            }});
            put("suffixes", new ArrayList<Object>() {{
                add("	е,ё,и,о,у,ы,э,ю   . . . . .");
                add("f	б,в,г,д,ж,з,й,к,л,м,н,п,р,с,т,ф,х,ц,ч,ш,щ,ъ   . . . . .");

                add("f	ь   -и -и . ю -и");
                add("m	ь   -я -ю -я -ем -е");

                add("	га,ка,ха,ча,ща,жа  -и -е -у -ой -е");
                add("	а   -ы -е -у -ой -е");
                add("	ия  -и -и -ю -ей -и");
                add("	я   -и -е -ю -ей -е");
                add("	ей  -я -ю -я -ем -е");
                add("	ий  -я -ю -я -ем -и");
                add("	й   -я -ю -я -ем -е");
                add("	б,в,г,д,ж,з,к,л,м,н,п,р,с,т,ф,х,ц,ч	 а у а ом е");
            }});
        }});
        put("middleName", new HashMap<String, List<Object>>() {{
            put("suffixes", new ArrayList<Object>() {{
                add("	ич   а  у  а  ем  е");
                add("	на  -ы -е -у -ой -е");
            }});
        }});
    }};

    static {
        prepareRules();
    }

    private static void prepareRules() {
        for (Map.Entry<String, Map<String, List<Object>>> type : rules.entrySet()) {
            for(Map.Entry<String, List<Object>> key : rules.get(type.getKey()).entrySet()) {
                for(int i = 0, n = rules.get(type.getKey()).get(key.getKey()).size(); i < n; i++) {
                    rules.get(type.getKey()).get(key.getKey()).set(i, rule(rules.get(type.getKey()).get(key.getKey()).get(i)));
                }
            }
        }
    }

    private static Map<String, List<String>> rule(Object rule) {
        final Matcher matcher = Pattern.compile(
                "^\\s*([fm]?)\\s*(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*$").matcher(rule.toString());
        if (matcher.matches()) {
            return new HashMap<String, List<String>>() {{
                put("sex", Arrays.asList(matcher.group(1)));
                put("test", Arrays.asList(matcher.group(2).split(",")));
                put("mods", Arrays.asList(matcher.group(3), matcher.group(4),
                        matcher.group(5), matcher.group(6), matcher.group(7)));
            }};
        }
        return Collections.<String, List<String>>emptyMap();
    }

    // склоняем слово по указанному набору правил и исключений
    public static String word(String word, String sex, String wordType, String gcase) {
        // исходное слово находится в именительном падеже
        if (gcase.equals(gcaseNom)) {
            return word;
        }

        // составные слова
        if (word != null && word.matches("[-]")) {
            String[] list = word.split("-");
            StringBuilder resultList = new StringBuilder();
            for(int i = 0, n = list.length; i < n; i++) {
                if (i > 0) {
                    resultList.append("-");
                }
                resultList.append(word(list[i], sex, wordType, gcase));
            }
            return resultList.toString();
        }

        // Иванов И. И.
        if (word != null && Pattern.compile("^[А-ЯЁ]\\.?$", Pattern.CASE_INSENSITIVE).matcher(word).matches()) {
            return word;
        }

        Map<String, List<Object>> localRules = rules.get(wordType);

        if (localRules.get("exceptions") !=  null) {
            String pick = pick(word, sex, gcase, localRules.get("exceptions"), true);
            if (pick != null) {
                return pick;
            }
        }
        String pick = pick(word, sex, gcase, localRules.get("suffixes"), false);
        return pick != null ? pick : word == null ? "" : word;
    }

    // выбираем из списка правил первое подходящее и применяем
    private static String pick(String word, String sex, String gcase, List<Object> rules, boolean matchWholeWord) {
        String wordLower = word == null ? "" : word.toLowerCase();
        for (int i = 0, n = rules.size(); i < n; i++) {
            if (ruleMatch(wordLower, sex, rules.get(i), matchWholeWord)) {
                return applyMod(word, gcase, rules.get(i));
            }
        }
        return null;
    }

    // проверяем, подходит ли правило к слову
    private static boolean ruleMatch(String word, String sex, final Object rule, boolean matchWholeWord) {
        final Map<String, List<String>> localRule = (Map<String, List<String>>) rule;
        if (localRule.get("sex").get(0).equals(sexM) && sex.equals(sexF)) {
            // male by default
            return false;
        }
        if (localRule.get("sex").get(0).equals(sexF) && !sex.equals(sexF)) {
            return false;
        }
        for (int i = 0, n = localRule.get("test").size(); i < n; i++) {
            String test = matchWholeWord ? word : word.substring(
                    Math.max(word.length() - localRule.get("test").get(i).length(), 0));
            if (test.equals(localRule.get("test").get(i))) {
                return true;
            }
        }
        return false;
    }

    // склоняем слово (правим окончание)
    private static String applyMod(final String word, String gcase, final Object rule) {
        final Map<String, List<String>> localRule = (Map<String, List<String>>) rule;
        final String mod;
        if (gcase.equals(gcaseNom)) {
            mod = ".";
        } else if(gcase.equals(gcaseGen)) {
            mod = localRule.get("mods").get(0);
        } else if(gcase.equals(gcaseDat)) {
            mod = localRule.get("mods").get(1);
        } else if(gcase.equals(gcaseAcc)) {
            mod = localRule.get("mods").get(2);
        } else if(gcase.equals(gcaseIns)) {
            mod = localRule.get("mods").get(3);
        } else if(gcase.equals(gcasePos)) {
            mod = localRule.get("mods").get(4);
        } else {
            throw new IllegalArgumentException("Unknown grammatic case: " + gcase);
        }
        String localWord = word;
        for(int i = 0, n = mod.length(); i < n; i++) {
            String c = mod.substring(i, i + 1);
            if (".".equals(c)) {
            } else if ("-".equals(c)) {
                localWord = localWord.substring(0, localWord.length() - 1);
            } else {
                localWord = localWord + c;
            }
        }
        return localWord;
    }
}
