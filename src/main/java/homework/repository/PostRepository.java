package homework.repository;

import homework.exception.NotFoundException;
import homework.model.Post;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private final Map<Long,Post> listPosts = new ConcurrentHashMap<>();
    private final AtomicLong ID = new AtomicLong();
    public List<Post> all() {
        if(listPosts.isEmpty()){
            return Collections.emptyList();
        }else {
            return listPosts.values().stream().toList();
        }
    }

    public Optional<Post> getById(long id) {
        Post post = listPosts.getOrDefault(id, null);
        return Optional.ofNullable(post);
    }

    public Post save(Post post) {
        if(post.getId()==0){
            post.setId(ID.incrementAndGet());
            listPosts.put(post.getId(), post);
        }else{
            listPosts.put(post.getId(), post);
        }
        return post;
    }

    public void removeById(long id) {
        Post item = listPosts.remove(id);
        if(item == null) {
            throw new NotFoundException("Удаление поста не возможно. Пост с id: "+id+" не найден.");
        }
    }
}
