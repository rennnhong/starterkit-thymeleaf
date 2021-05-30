package indi.rennnhong.staterkit.rbac.repository;

import indi.rennnhong.staterkit.rbac.entity.Api;
import indi.rennnhong.staterkit.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface ApiRepository extends BaseRepository<Api, UUID> {

    Optional<Api> findByUrlAndHttpMethod(String uri, String httpMethod);

}
