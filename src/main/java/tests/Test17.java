package tests;

import ru.skillbench.tasks.basics.text.WordCounterImpl;

import java.io.PrintStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test17 {
    public static void main(String[] args) {
    /*
        String text= " GNU LESSER GENERAL PUBLIC LICENSE\n" +
                "                       Version 3, 29 June 2007\n" +
                "\n" +
                " Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>\n" +
                " Everyone is permitted to copy and distribute verbatim copies\n" +
                " of this license document, but changing it is not allowed.\n" +
                "\n";

                "\n" +
                "  This version of the GNU Lesser General Public License incorporates\n" +
                "the terms and conditions of version 3 of the GNU General Public\n" +
                "License, supplemented by the additional permissions listed below.\n" +
                "\n" +
                "  0. Additional Definitions.\n" +
                "\n" +
                "  As used herein, \"this License\" refers to version 3 of the GNU Lesser\n" +
                "General Public License, and the \"GNU GPL\" refers to version 3 of the GNU\n" +
                "General Public License.\n" +
                "\n" +
                "  \"The Library\" refers to a covered work governed by this License,\n" +
                "other than an Application or a Combined Work as defined below.\n" +
                "\n" +
                "  An \"Application\" is any work that makes use of an interface provided\n" +
                "by the Library, but which is not otherwise based on the Library.\n" +
                "Defining a subclass of a class defined by the Library is deemed a mode\n" +
                "of using an interface provided by the Library.\n" +
                "\n" +
                "  A \"Combined Work\" is a work produced by combining or linking an\n" +
                "Application with the Library.  The particular version of the Library\n" +
                "with which the Combined Work was made is also called the \"Linked\n" +
                "Version\".\n" +
                "\n" +
                "  The \"Minimal Corresponding Source\" for a Combined Work means the\n" +
                "Corresponding Source for the Combined Work, excluding any source code\n" +
                "for portions of the Combined Work that, considered in isolation, are\n" +
                "based on the Application, and not on the Linked Version.\n" +
                "\n" +
                "  The \"Corresponding Application Code\" for a Combined Work means the\n" +
                "object code and/or source code for the Application, including any data\n" +
                "and utility programs needed for reproducing the Combined Work from the\n" +
                "Application, but excluding the System Libraries of the Combined Work.\n" +
                "\n" +
                "  1. Exception to Section 3 of the GNU GPL.\n" +
                "\n" +
                "  You may convey a covered work under sections 3 and 4 of this License\n" +
                "without being bound by section 3 of the GNU GPL.\n" +
                "\n" +
                "  2. Conveying Modified Versions.\n" +
                "\n" +
                "  If you modify a copy of the Library, and, in your modifications, a\n" +
                "facility refers to a function or data to be supplied by an Application\n" +
                "that uses the facility (other than as an argument passed when the\n" +
                "facility is invoked), then you may convey a copy of the modified\n" +
                "version:\n" +
                "\n" +
                "   a) under this License, provided that you make a good faith effort to\n" +
                "   ensure that, in the event an Application does not supply the\n" +
                "   function or data, the facility still operates, and performs\n" +
                "   whatever part of its purpose remains meaningful, or\n" +
                "\n" +
                "   b) under the GNU GPL, with none of the additional permissions of\n" +
                "   this License applicable to that copy.\n" +
                "\n" +
                "  3. Object Code Incorporating Material from Library Header Files.\n" +
                "\n" +
                "  The object code form of an Application may incorporate material from\n" +
                "a header file that is part of the Library.  You may convey such object\n" +
                "code under terms of your choice, provided that, if the incorporated\n" +
                "material is not limited to numerical parameters, data structure\n" +
                "layouts and accessors, or small macros, inline functions and templates\n" +
                "(ten or fewer lines in length), you do both of the following:\n" +
                "\n" +
                "   a) Give prominent notice with each copy of the object code that the\n" +
                "   Library is used in it and that the Library and its use are\n" +
                "   covered by this License.\n" +
                "\n" +
                "   b) Accompany the object code with a copy of the GNU GPL and this license\n" +
                "   document.\n" +
                "\n" +
                "  4. Combined Works.\n" +
                "\n" +
                "  You may convey a Combined Work under terms of your choice that,\n" +
                "taken together, effectively do not restrict modification of the\n" +
                "portions of the Library contained in the Combined Work and reverse\n" +
                "engineering for debugging such modifications, if you also do each of\n" +
                "the following:\n" +
                "\n" +
                "   a) Give prominent notice with each copy of the Combined Work that\n" +
                "   the Library is used in it and that the Library and its use are\n" +
                "   covered by this License.\n" +
                "\n" +
                "   b) Accompany the Combined Work with a copy of the GNU GPL and this license\n" +
                "   document.\n" +
                "\n" +
                "   c) For a Combined Work that displays copyright notices during\n" +
                "   execution, include the copyright notice for the Library among\n" +
                "   these notices, as well as a reference directing the user to the\n" +
                "   copies of the GNU GPL and this license document.\n" +
                "\n" +
                "   d) Do one of the following:\n" +
                "\n" +
                "       0) Convey the Minimal Corresponding Source under the terms of this\n" +
                "       License, and the Corresponding Application Code in a form\n" +
                "       suitable for, and under terms that permit, the user to\n" +
                "       recombine or relink the Application with a modified version of\n" +
                "       the Linked Version to produce a modified Combined Work, in the\n" +
                "       manner specified by section 6 of the GNU GPL for conveying\n" +
                "       Corresponding Source.\n" +
                "\n" +
                "       1) Use a suitable shared library mechanism for linking with the\n" +
                "       Library.  A suitable mechanism is one that (a) uses at run time\n" +
                "       a copy of the Library already present on the user's computer\n" +
                "       system, and (b) will operate properly with a modified version\n" +
                "       of the Library that is interface-compatible with the Linked\n" +
                "       Version.\n" +
                "\n" +
                "   e) Provide Installation Information, but only if you would otherwise\n" +
                "   be required to provide such information under section 6 of the\n" +
                "   GNU GPL, and only to the extent that such information is\n" +
                "   necessary to install and execute a modified version of the\n" +
                "   Combined Work produced by recombining or relinking the\n" +
                "   Application with a modified version of the Linked Version. (If\n" +
                "   you use option 4d0, the Installation Information must accompany\n" +
                "   the Minimal Corresponding Source and Corresponding Application\n" +
                "   Code. If you use option 4d1, you must provide the Installation\n" +
                "   Information in the manner specified by section 6 of the GNU GPL\n" +
                "   for conveying Corresponding Source.)\n" +
                "\n" +
                "  5. Combined Libraries.\n" +
                "\n" +
                "  You may place library facilities that are a work based on the\n" +
                "Library side by side in a single library together with other library\n" +
                "facilities that are not Applications and are not covered by this\n" +
                "License, and convey such a combined library under terms of your\n" +
                "choice, if you do both of the following:\n" +
                "\n" +
                "   a) Accompany the combined library with a copy of the same work based\n" +
                "   on the Library, uncombined with any other library facilities,\n" +
                "   conveyed under the terms of this License.\n" +
                "\n" +
                "   b) Give prominent notice with the combined library that part of it\n" +
                "   is a work based on the Library, and explaining where to find the\n" +
                "   accompanying uncombined form of the same work.\n" +
                "\n" +
                "  6. Revised Versions of the GNU Lesser General Public License.\n" +
                "\n" +
                "  The Free Software Foundation may publish revised and/or new versions\n" +
                "of the GNU Lesser General Public License from time to time. Such new\n" +
                "versions will be similar in spirit to the present version, but may\n" +
                "differ in detail to address new problems or concerns.\n" +
                "\n" +
                "  Each version is given a distinguishing version number. If the\n" +
                "Library as you received it specifies that a certain numbered version\n" +
                "of the GNU Lesser General Public License \"or any later version\"\n" +
                "applies to it, you have the option of following the terms and\n" +
                "conditions either of that published version or of any later version\n" +
                "published by the Free Software Foundation. If the Library as you\n" +
                "received it does not specify a version number of the GNU Lesser\n" +
                "General Public License, you may choose any version of the GNU Lesser\n" +
                "General Public License ever published by the Free Software Foundation.\n" +
                "\n" +
                "  If the Library as you received it specifies that a proxy can decide\n" +
                "whether future versions of the GNU Lesser General Public License shall\n" +
                "apply, that proxy's public statement of acceptance of any version is\n" +
                "permanent authorization for you to choose that version for the\n" +
                "Library." ;

                 */

        /*
        String text="Старичок к старухе воротился,\n" +
                "Что ж? пред ним царские палаты,\n" +
                "В палатах видит свою старуху,\n" +
                "За столом сидит она царицей,\n" +
                "Служат ей бояре да дворяне,\n" +
                "Наливают ей заморские вина;\n" +
                "Заедает она пряником печатным;\n" +
                "Вкруг её стоит грозная стража,\n" +
                "На плечах топорики держат.\n" +
                "Как увидел старик-испугался!\n" +
                "В ноги он старухе поклонился,\n" +
                "Молвил: \"Здравствуй, грозная царица!\n" +
                "Ну теперь твоя душенька довольна?\"\n" +
                "На него старуха не взглянула,\n" +
                "Лишь с очей прогнать его велела.\n" +
                "Подбежали бояре и дворяне,\n" +
                "Старика взашей затолкали.\n" +
                "А в дверях-то стража подбежала,\n" +
                "Топорами чуть не изрубила,\n" +
                "А народ-то над ним насмеялся:\n" +
                "\"Поделом тебе, старый невежа!\n" +
                "Впредь тебе, невежа, наука:\n" +
                "Не садися не в свои сани!\"\n" +
                "\n" +
                "Вот неделя, другая проходит,\n" +
                "Ещё пуще старуха вздурилась:\n" +
                "Царедворцев за мужем посылает.\n" +
                "Отыскали старика, привели к ней.\n" +
                "Говорит старику старуха:\n" +
                "\"Воротись, поклонися рыбке.\n" +
                "Не хочу быть вольною царицей,\n" +
                "Хочу быть владычицей морскою,\n" +
                "Чтобы жить мне в окияне-море,\n" +
                "Чтоб служила мне рыбка золотая\n" +
                "И была б у меня на посылках\"." +
                "Старик не осмелился перечить,\n" +
                "Не дерзнул поперёк слова молвить.\n" +
                "Вот идёт он к синему морю,\n" +
                "Видит, на море чёрная буря:\n" +
                "Так и вздулись сердитые волны,\n" +
                "Так и ходят, так воем и воют.\n" +
                "Стал он кликать золотую рыбку.\n" +
                "Приплыла к нему рыбка, спросила:\n" +
                "\"Чего тебе надобно, старче?\"\n" +
                "Ей старик с поклоном отвечает:\n" +
                "\"Смилуйся, государыня рыбка!\n" +
                "Что мне делать с проклятою бабой?\n" +
                "Уж не хочет быть она царицей,\n" +
                "Хочет быть владычицей морскою:\n" +
                "Чтобы жить ей в окияне-море,\n" +
                "Чтобы ты сама ей служила\n" +
                "И была бы у ней на посылках\".\n" +
                "Ничего не сказала рыбка,\n" +
                "Лишь хвостом по воде плеснула\n" +
                "И ушла в глубокое море.\n" +
                "Долго у моря ждал он ответа,\n" +
                "Не дождался, к старухе воротился\n" +
                "Глядь: опять перед ним землянка;\n" +
                "На пороге сидит его старуха,\n" +
                "А пред нею разбитое корыто.";

         */
        //String text="mama papa mama";
        //String new_text=text.replaceAll("[-,.()^:]"," ");
        //Pattern pattern=Pattern.compile("[^<][a-z]{1,}[^>]");
        //Matcher matcher=pattern.matcher(text.toLowerCase());

        //String text="but, projects, data, software, translated, approach, three, without, that, either, later, model, from, implement, different, directly, case, development, in, remain, this, is, it, each, relatively, must, carry, however, other, be, independent, classes, according, direct, affecting, storage, necessarily, out, can, into, and, of, design, course, emphasize, physical, class, table, early, phases, allows, a, or, may, change, column, structures, also, consistent, possible, technology, many, ultimately, structure, logical, ansi, conceptual, the, significance, such, with, detailed, perspectives, stages, translation, attributes, objectives, to, entity";
        //String text="сикински, подобных, новые, ещё, пластика, пластик, сталь, этой, видят, разработкой, заявил, со, учёными, вариантов, мосты, тонн, приводится, множество, мосту, лучший, уран, зелёный, ногу, отметить, разрушающими, в, плазменные, выполнен, раза, и, движение, к, наземных, совместно, тяжёлых, последующие, машина, наше, танков, о, такие, конструкция, с, армия, применения, проехал, которые, они, урана, возможностей, переработанный, сообществом, который, поэтому, средств, окружающей, конструкции, переработанных, которого, это, индустрией, поколения, коррозии, 34, стальных, m1, дерево, инспекции, проект, исследователей, занималась, имеет, нью, rutgers, заботится, штата, сохранности, транспортных, вызванные, чтобы, станций, следовательно, решений, дж, отражает, весом, или, упомянуты, анонсировано, военные, отличие, стивен, мире, правительством, состоящего, материалом, газовой, на, испытать, последнего, скептицизм, abrams, зря, применённый, американская, не, восстановлении, лицевыми, international, изменений, относительно, первого, итоге, модели, высокопрочного, утилизации, пластинами, абрамс, пронизывающие, университета, кажущийся, отходов, самого, танк, требует, адекватных, пучки, для, могут, пластиковых, старый, годы, серьёзных, обслуживания, сотрудничество, завершение, моста, от, выступил, поиске, один, university, практически, идти, продемонстрировать, материалы, самых, устойчива, компания, строительства, как, бетон, атомных, регулярном, 70, целью, мост, по, термопластика, заменит, нему, последний, перешагивают, сократить, бутылок, полковник, временем, турбиной, современные, своеобразно, джерси, инфраструктурных, броню, затраты, несмотря, боеголовками, предметов, старается, сфер, помимо, способ, обеднённого, отметку, метод, среды, axion, минами, нуждаются, из";
        String text="adipiscing, commodo, odio, accumsan, nonummy, augue, erat, facilisis, euismod, lorem, vero, vel, ea, molestie, feugiat, ut, wisi, volutpat, enim, exerci, consequat, ad, lobortis, in, zzril, velit, dignissim, facilisi, vulputate, iriure, feugait, et, esse, eu, at, ex, ipsum, eum, elit, nostrud, luptatum, ullamcorper, laoreet, qui, minim, veniam, dolor, sed, duis, hendrerit, sit, autem, nisl, tincidunt, aliquam, magna, praesent, aliquip, consectetuer, te, dolore, nibh, delenit, amet, illum, quis, iusto, nulla, eros, tation";
        ArrayList<String> list=new ArrayList<>();
        String s=text.toLowerCase();
        String[] spl=s.split("[\\s]+|[\\n]+|[\\t]+");
        for (int i = 0; i < spl.length; i++) {
            int l=spl[i].length();
            if(!spl[i].equals("") && spl[i].startsWith("<")==false && spl[i].endsWith(">")==false) {
                list.add(spl[i].replaceAll("[.,():^&?;\\-!\"]",""));
            }
        }
        System.out.println(list.size());
        Map<String,Long> map=new HashMap<>();
        Long item;
        for (String swd:list
             ) {
            item=map.get(swd);
            if(item==null){
                map.put(swd,1L);
            }
            else
                map.put(swd,item+1);
        }

        for (Map.Entry<String,Long> pair:map.entrySet()
             ) {
            System.out.println(pair.getKey()+" "+pair.getValue());
        }
        System.out.println("_______________________________");

        CompOneCount compOneCount=new CompOneCount();
    List<Map.Entry<String,Long>> list2=Test17.sort(map,compOneCount);
    PrintStream printStream=new PrintStream(System.out);
        Test17.print(list2,printStream);

        /*
        List<Map.Entry<String,Long>> list1=new ArrayList<>(map.entrySet());
        CompOneCount compOneCount=new CompOneCount();
        CompTwoString compTwoString=new CompTwoString();
        Collections.sort(list1,compOneCount.thenComparing(compTwoString));
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i).getValue()+" "+list1.get(i).getKey());
        }
         */
    }
    public static  <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        Map<K,V> map_two=new HashMap<>();
        map_two.putAll(map);
        List<Map.Entry<K,V>> lis=new ArrayList<>(map_two.entrySet());
        Collections.sort(lis,comparator);
        return lis;
    }

    static class  CompOneCount implements Comparator<Map.Entry<String,Long>>{
        @Override
        public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    }
    static class CompTwoString implements Comparator<Map.Entry<String,Long>>{

        @Override
        public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    }
    public static  <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        List<Map.Entry<K, V>> list=entries;
        for (int i = 0; i < list.size(); i++) {
            ps.println(list.get(i).getKey()+" "+list.get(i).getValue());
        }
    }
}
