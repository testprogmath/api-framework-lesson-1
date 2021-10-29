package ru.gb.tests;

import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.gb.dto.CreateGroupRequest;
import ru.gb.dto.Role;

import static io.restassured.RestAssured.given;

public class PostGroupTests {
    static String token;
    static CreateGroupRequest request;

    @BeforeClass
    public static void beforeClass() {

        Role role = new Role();
        role.setDescription("Привилегия сохранения роли");
        role.setRoleName("ROLE_MS_AUTH_GROUP_SAVE");

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);
        request = new CreateGroupRequest();
        request.setId(1000);
        request.setDescription("абракадабра");
        request.setGroupName("DEFAULT_USER_ROLES" + RandomUtils.nextInt());
        request.setRoles(roles);
        token = given()
                .header("Content-Type", "application/json")
                .body("{ \"email\": \"admin@myproevent.ru\", \"password\": \"ProEvent!Ramses2\"}")
                .post("http://178.249.69.107:8762/ms-auth/api/v1/auth/login")
                .prettyPeek()
                .then()
                .extract()
                .body()
                .jsonPath()
                .get("token");
    }

    //todo: 1. попробовать получить токен - если не получится, оставить как есть
    //todo:  2. Надо cделать тест-дизайн
    //todo: погуглить глобальные переменные Rest-assured RestAssured.baseUrl=
    //todo: сделать проверки не только на статус-код, но и на тело ответа
    //todo: если тест что-то создает, он должен за собой это удалять
    @Test
    public void createGroupPositiveTest() {
        given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(request)
                // логирование запроса
                .log()
                .all()
                .when()
                .post("http://178.249.69.107:8762/ms-auth/api/v1/auth/groups")
                //логирование ответа
                .prettyPeek()
                .then()
                // проверка, что статус код = 200
                .statusCode(200);
    }

    @After
    public void tearDown() throws Exception {
        //удаление созданной группы
    }
}
