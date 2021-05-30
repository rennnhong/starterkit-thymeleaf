package indi.rennnhong.staterkit.rbac.repository;

import indi.rennnhong.staterkit.common.BaseRepository;
import indi.rennnhong.staterkit.rbac.entity.Role;
import indi.rennnhong.staterkit.rbac.entity.User;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User, UUID> {

    Optional<User> findByAccount(String account);

    boolean existsByAccount(String account);

    Collection<User> findAllByRolesIsIn(Collection<Role> role);

}
