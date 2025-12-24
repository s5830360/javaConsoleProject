import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        String name = "";
        ArrayList<String> list = new ArrayList<String>();

        while (i != 0) {
            System.out.println("==== 회원 관리 프로그램 ====");
            System.out.println("1. 회원 등록");
            System.out.println("2. 회원 목록 조회");
            System.out.println("3. 회원 수정");
            System.out.println("4. 회원 삭제");
            System.out.println("0. 종료");
            System.out.print("선택 >> ");

            i = scanner.nextInt();
            switch(i) {
                case 1:
                    System.out.print("이름 입력 : ");
                    name = scanner.nextLine();
                    if(name.isEmpty()) {
                        System.out.println("등록에 실패하였습니다.");
                        break;
                    }
                    list.add(name);
                    System.out.println("회원 등록 완료 (ID:" + list.toArray().length + ")");
                    break;
                case 2:
                    System.out.println("[회원 목록]");
                    if(list.isEmpty()) {
                        System.out.println("등록된 회원이 없습니다.");
                    }
                    for (int j = 0; j < list.toArray().length; j++) {
                        System.out.println(list.indexOf(list.get(j) + " - " + list.get(j)));
                    }
                    break;
                case 3:
                    System.out.print("수정할 회원 ID : ");
                    int id = scanner.nextInt();
                    if(id >= list.size()) {
                        System.out.println("해당 ID의 회원이 없습니다.");
                        break;
                    }
                    System.out.print("새 이름 입력 : ");
                    name = scanner.nextLine();
                    list.set(id, name);
                    System.out.println("회원 정보 수정 완료");
                    break;
                case 4:
                    System.out.print("삭제할 회원 ID : ");
                    id = scanner.nextInt();
                    if(id >= list.size()) {
                        System.out.println("존재하지 않는 ID입니다.");
                    }
                    list.remove(list.get(id));
                    System.out.println("회원 삭제 완료");
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 메뉴로 돌아갑니다.");
            }
        }
    }
}