package indi.rennnhong.staterkit.rbac.repository;

import indi.rennnhong.staterkit.rbac.entity.Action;
import indi.rennnhong.staterkit.rbac.entity.Permission;
import indi.rennnhong.staterkit.common.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ActionRepository extends BaseRepository<Action, UUID> {

    List<Action> findAllByPermission(Permission permission);

}
