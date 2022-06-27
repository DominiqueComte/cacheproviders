package ch.frankel.blog.cache;

import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheTests {

    private static final Logger log = LoggerFactory.getLogger(CacheTests.class);

    static Stream<CacheProvider<Long, String>> cacheProviders() {
        return Stream.of(
                new JavaCachingSystemProvider<>(),
                new EhCacheProvider<>(Long.class, String.class),
                new GuavaProvider(),
                new CaffeineProvider(),
                new InfinispanProvider(),
                new CoherenceProvider(),
                new IgniteProvider(),
                new GeodeProvider(),
                new HazelcastProvider(),
                new RedisProvider()
        );
    }

    @ParameterizedTest
    @MethodSource("cacheProviders")
    void whenPutShouldGet(CacheProvider<Long, String> provider) throws IOException {
        log.info("Cache provider: {}", provider.getClass().getName());
        provider.put(1L, "One");
        var result = provider.get(1L);
        assertEquals("One", result);
        provider.close();
    }
}
