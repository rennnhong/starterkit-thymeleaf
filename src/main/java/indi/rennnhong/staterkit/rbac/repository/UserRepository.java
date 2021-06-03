package indi.rennnhong.staterkit.rbac.repository;

import indi.rennnhong.staterkit.common.BaseRepository;
import indi.rennnhong.staterkit.module.student.model.entity.Student;
import indi.rennnhong.staterkit.rbac.entity.Role;
import indi.rennnhong.staterkit.rbac.entity.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User, UUID>, DataTablesRepository<User,UUID> {

    Optional<User> findByAccount(String account);

    boolean existsByAccount(String account);

    Collection<User> findAllByRolesIsIn(Collection<Role> role);

}
