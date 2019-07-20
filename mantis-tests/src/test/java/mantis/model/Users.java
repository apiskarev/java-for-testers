package mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Users extends ForwardingSet<User> {

    @Override
    protected Set<User> delegate() {
        return delegate;
    }

    private Set<User> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<>();
    }

    public Users(Collection<User> users) {
        this.delegate = new HashSet<>(users);
    }

    public Users withAdded(User user){
        Users users = new Users(this);
        users.add(user);
        return users;
    }

    public Users without(User user){
        Users users = new Users(this);
        users.remove(user);
        return users;
    }
}
