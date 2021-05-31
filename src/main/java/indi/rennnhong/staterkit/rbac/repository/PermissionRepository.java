package indi.rennnhong.staterkit.rbac.repository;

import indi.rennnhong.staterkit.rbac.entity.Permission;
import indi.rennnhong.staterkit.common.BaseRepository;

import java.util.UUID;

public interface PermissionRepository extends BaseRepository<Permission, UUID> {

    boolean existsByParentId(UUID id);

}
