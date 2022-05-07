package framework.com.example.demo.model.network.response;

import framework.com.example.demo.model.entity.Soldier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeApiResponse {
        private String name;

        private String price;

        private String percent;

        private List<Soldier> soldiers;
}
