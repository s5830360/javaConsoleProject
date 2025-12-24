import java.util.ArrayList;
import java.util.Scanner;

class Member {
    public int id;
    public String name;
    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        String name = "";
        ArrayList<Member> list = new ArrayList<>();

        while (i != 0) {
            System.out.println("==== 회원 관리 프로그램 ====");
            System.out.println("1. 회원 등록");
            System.out.println("2. 회원 목록 조회");
            System.out.println("3. 회원 수정");
            System.out.println("4. 회원 삭제");
            System.out.println("0. 종료");
            System.out.print("선택 >> ");

            i = scanner.nextInt();
            scanner.nextLine(); // nextInt가 숫자만 읽고 엔터를 안 읽어서 버퍼 비우기 용
            switch(i) {
                case 1:
                    System.out.print("이름 입력 : ");
                    name = scanner.nextLine();
                    if(name.isEmpty()) {
                        System.out.println("등록에 실패하였습니다.");
                        break;
                    }
                    Member member = new Member(list.size() + 1, name);
                    list.add(member);
                    System.out.println("회원 등록 완료 (ID:" + list.size() + ")");
                    break;
                case 2:
                    System.out.println("[회원 목록]");
                    if(list.isEmpty()) {
                        System.out.println("등록된 회원이 없습니다.");
                    }
                    for (int j = 0; j < list.size(); j++) {
                        System.out.println((j + 1) + " - " + list.get(j).name);
                    }
                    break;
                case 3:
                    System.out.print("수정할 회원 ID : ");
                    int id = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if(id > list.size()) {
                        System.out.println("해당 ID의 회원이 없습니다.");
                        break;
                    }
                    System.out.print("새 이름 입력 : ");
                    name = scanner.nextLine();
                    Member newMember = new Member(id, name);
                    list.set(newMember.id, newMember);
                    System.out.println("회원 정보 수정 완료");
                    break;
                case 4:
                    System.out.print("삭제할 회원 ID : "); // id = index + 1
                    id = scanner.nextInt();
                    scanner.nextLine();
                    if(id > list.size()) {
                        System.out.println("존재하지 않는 ID입니다.");
                        break;
                    }
                    list.remove(id-1); // 인덱스로도 삭제 가능?
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