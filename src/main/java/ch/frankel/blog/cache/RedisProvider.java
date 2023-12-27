package ch.frankel.blog.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.net.ServerSocket;

public class RedisProvider implements CacheProvider<Long, String> {

	RedisServer server;
	private final JedisPool pool;

	public RedisProvider() {
		// bind address for the Redis server
		final String serverIp = "127.0.0.1";
		// find free port
		final int port;
		try {
			final ServerSocket serverSocket = new ServerSocket(0);
			port = serverSocket.getLocalPort();
			serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Could not get a free port for the Redis server", e);
		}

		try {
			server = RedisServer.newRedisServer().setting("bind " + serverIp).port(port).build(); // bind to ignore Windows firewall popup each time the server starts
		} catch (IOException e) {
			throw new RuntimeException("Could not build the embedded Redis server", e);
		}

		try {
			server.start();
		} catch (IOException e) {
			throw new RuntimeException("Could not start the embedded Redis server", e);
		}

		pool = new JedisPool(serverIp, port);
	}

	@Override
	public void put(Long key, String value) {
		Jedis jedis = pool.getResource();
		try {
			jedis.set(String.valueOf(key), value);
		} catch (JedisException e) {
			// if something wrong happens, return it to the pool
			pool.returnBrokenResource(jedis);
			jedis = null;
		} finally {
			// it's important to return the Jedis instance to the pool once you've finished using it
			if (null != jedis) {
				pool.returnResource(jedis);
			}
		}
	}

	@Override
	public String get(Long key) {
		Jedis jedis = pool.getResource();
		return jedis.get(String.valueOf(key));
	}

	@Override
	public void close() throws IOException {
		pool.close();
		server.stop();
	}

}
