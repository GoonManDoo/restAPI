package com.restApi.restApi.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    // 테스트 데이터
    static {
        users.add(new User(1, "Kenneth", new Date(), "pass1", "701010-1111111"));
        users.add(new User(2, "Alice", new Date(), "pass1", "701010-1111111"));
        users.add(new User(3, "Elena", new Date(), "pass1", "701010-1111111"));
    }

    // 전체 조회
    public List<User> findAll() {
        return users;
    }


    // 저장
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }


    // 단건 조회
    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    // 삭제
    public User deleteById(int id) {

        // 열거형
        Iterator<User> iterator = users.iterator();
        // 순차적으로 1개씩 데이터 가져오기
        while (iterator.hasNext()); {
            User user = iterator.next();

            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    // 수정
    public User update(int id, User user) {
        for (User storedUser : users) {
            if (storedUser.getId() == id) {
                storedUser.setName(user.getName());
                //storedUser.setPassword(user.getPassword());

                return storedUser;
            }
        }

        return null;
    }

}
