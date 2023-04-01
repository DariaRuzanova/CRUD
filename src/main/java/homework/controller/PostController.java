package homework.controller;

import com.google.gson.Gson;
import homework.model.Post;
import homework.service.PostService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;


public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final Gson GSON = new Gson();
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        response.getWriter().print(GSON.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.getById(id);
        response.getWriter().print(GSON.toJson(data));
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var post = GSON.fromJson(body, Post.class);
        final var data = service.save(post);
        response.getWriter().print(GSON.toJson(data));
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        service.removeById(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
