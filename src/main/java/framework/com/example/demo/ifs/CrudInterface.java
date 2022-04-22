package framework.com.example.demo.ifs;

import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.UserApiRequest;
import framework.com.example.demo.model.network.response.UserApiResponse;

public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
