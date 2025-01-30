package integrationTest.api.utils;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class BodyBuilder {
    private final Map<String, Object> body;

    public static BodyBuilder with(String path, Object value) {
        var builder = new BodyBuilder(new HashMap<>());
        builder.body.put(path, value);
        return builder;
    }

    public Object build() {
        return body;
    }
}
