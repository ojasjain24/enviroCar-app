package org.envirocar.remote.repository;

import org.envirocar.core.entity.User;
import org.envirocar.core.entity.UserStatistic;
import org.envirocar.core.repository.UserStatisticRepository;
import org.envirocar.remote.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * @author dewall
 */
@Singleton
public class RemoteUserStatisticRepository extends RemoteRepository<UserService> implements UserStatisticRepository {

    private final UserService userService;

    /**
     * Constructor.
     *
     * @param userService
     */
    @Inject
    public RemoteUserStatisticRepository(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @Override
    public Observable<UserStatistic> user(User user) {
        Call<UserStatistic> call = userService.getUserStatistic(user.getUsername());
        return wrapObservableHandling(() -> wrapExecuteCallReturnBody(call));
    }

}
