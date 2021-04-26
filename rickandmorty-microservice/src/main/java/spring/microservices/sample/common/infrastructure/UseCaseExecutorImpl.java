package spring.microservices.sample.common.infrastructure;

import org.springframework.stereotype.Service;
import spring.microservices.sample.common.application.UseCase;
import spring.microservices.sample.common.application.UseCaseExecutor;

import java.util.function.Function;

/**
 * Default implementation for use-case executor interface.
 *
 * @author Miguel A. Vila
 */
@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {

    @Override
    public <R, I extends UseCase.InputValues, O extends UseCase.OutputValues> R execute(
        UseCase<I, O> useCase, I input, Function<O, R> outputMapper) {
        O output = useCase.execute(input);
        return outputMapper.apply(output);
    }

}
