package indi.rennnhong.staterkit.rbac.repository;

import indi.rennnhong.staterkit.common.BaseRepository;
import indi.rennnhong.staterkit.rbac.entity.Role;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends BaseRepository<Role, UUID> {

    List<Role> findAllByIdIn(List<UUID> ids);

    Set<Role> findAllByCodeIn(Set<String> code);
}
