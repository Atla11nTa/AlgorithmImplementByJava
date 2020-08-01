package 笔试汇总.猿辅导2020试卷3;

import java.util.*;

public class Main1 {
    static class banji{
        String banjiName;
        String teacherId;
        List<int[]> teacherTime;
        HashMap<String, List<int[]>> studentTime;
        HashSet<String> studentId;
        int studentCount;

        banji() {
            teacherTime = new ArrayList<>();
            studentTime = new HashMap<>();
            studentId = new HashSet<>();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 记录数
        int N = in.nextInt();
        // 班级数
        int M = in.nextInt();

        banji[] banjis = new banji[M];
        for (int i = 0; i < M; i++) {
            banjis[i] = new banji();
            banjis[i].studentCount = in.nextInt();
            banjis[i].teacherId = in.next();
            banjis[i].banjiName = in.next();
            for (int j = 0; j < banjis[i].studentCount; j++) {
                banjis[i].studentId.add(in.next());
            }
        }
        String type;
        String id;
        int time;
        // 保存记录
        for (int i = 0; i < N; i++) {
            type = in.next();
            id = in.next();
            time = in.nextInt();
            // 遍历班级
            for (int j = 0; j < M; j++) {
                // 这条记录是老师
                if (banjis[j].teacherId.equals(id)) {
                    int timeLeft = type.equals("IN") ? time : -1;
                    int timeRight = type.equals("OUT") ? time : -1;
                    banjis[j].teacherTime.add(new int[]{timeLeft, timeRight});
                    break;
                }
                // 这条记录是学生
                if (banjis[j].studentId.contains(id)) {
                    int timeLeft = type.equals("IN") ? time : -1;
                    int timeRight = type.equals("OUT") ? time : -1;
                    List<int[]> list = banjis[j].studentTime.get(id);
                    // 没有记录就新加一个
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(new int[]{timeLeft, timeRight});
                    banjis[j].studentTime.put(id, list);
                }
            }
        }
        // 计算每个班老师的出勤分钟数
    }
}
