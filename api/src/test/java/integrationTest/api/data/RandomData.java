package integrationTest.api.data;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RandomData {
    private static final Faker fake = Faker.instance();

    public static String name() {
        return fake.funnyName().name();
    }
}
