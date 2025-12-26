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

class MemberRepository {
    private ArrayList<Member> members;

    public MemberRepository(ArrayList<Member> members) {
        this.members = members;
    }

    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.id == id) {
                return member;
            }
        }
        return null;
    }

    public void save(Member member) {
        members.add(member);
    }

    public void delete(Member member) {
        members.remove(member);
    }

    public ArrayList<Member> findAll() {
        return members;
    }
}

class MemberService {
    private MemberRepository repository;

    private int id = 1;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member register(String name) {
        if(name == null || name.isEmpty()) {
            return null;
        }

        Member member = new Member(id, name);
        repository.save(member);
        id++;

        return member;
    }

    public boolean update(int id, String name) {
        Member updateMember = repository.findMemberById(id);

        if(updateMember == null) {
            return false;
        } else {
            updateMember.name = name;
            return true;
        }
    }

    public boolean delete(int id) {
        Member deleteMember = repository.findMemberById(id);

        if(deleteMember == null) {
            return false;
        } else {
            repository.delete(deleteMember);
            return true;
        }
    }

    public ArrayList<Member> findAll() {
        return repository.findAll();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 1; // 메뉴 선택
        String name = ""; // 이름
        ArrayList<Member> list = new ArrayList<>();
        MemberRepository repo = new MemberRepository(list);
        MemberService service = new MemberService(repo);

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
                case 1: // 회원 등록 create
                    System.out.print("이름 입력 : ");
                    name = scanner.nextLine();

                    Member member = service.register(name);

                    if(member == null) {
                        System.out.println("등록에 실패하였습니다.");
                        break;
                    } else {
                        System.out.println("회원 등록 완료 (ID:" + member.id + ")");
                    }
                    break;
                case 2: // 회원 목록 조회 read
                    System.out.println("[회원 목록]");

                    ArrayList<Member> members = service.findAll();

                    if(members.isEmpty()) {
                        System.out.println("등록된 회원이 없습니다.");
                    }

                    for (Member m : members) {
                        System.out.println(m.id + " - " + m.name);
                    }
                    break;
                case 3: // 회원 수정 update
                    System.out.print("수정할 회원 ID : ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("새 이름 입력 : ");
                    name = scanner.nextLine();

                    if(service.update(updateId, name)) {
                        System.out.println("회원 정보 수정 완료");
                    } else {
                        System.out.println("해당 ID의 회원이 없습니다.");
                        break;
                    }
                    break;
                case 4: // 회원 삭제 delete
                    System.out.print("삭제할 회원 ID : "); // id = index + 1
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    if(service.delete(deleteId)) {
                        System.out.println("회원 삭제 완료");
                    } else {
                        System.out.println("존재하지 않는 ID입니다.");
                        break;
                    }
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