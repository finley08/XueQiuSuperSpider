package entryFirst;

import org.decaywood.acceptor.AbstractAcceptor;
import org.decaywood.entity.Industry;
import org.decaywood.utils.DatabaseAccessor;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class IndustryInfoToDBAcceptor extends AbstractAcceptor<Industry> {

    public IndustryInfoToDBAcceptor() {
    }

    ;

    @Override
    protected void consumLogic(Industry industry) throws Exception {

        if (industry == null) {
            return;
        }

        Connection connection = DatabaseAccessor.Holder.ACCESSOR.getConnection();

        StringBuilder builder = new StringBuilder();
        builder.append("insert into industry ")
                .append("(industry_name, industry_info,gmt_create,gmt_modified) ")
                .append("values (?, ?,?,?)");
        String sql = builder.toString();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, industry.getIndustryName());
        statement.setString(2, industry.getIndustryInfo());
        statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

        statement.execute();
        DatabaseAccessor.Holder.ACCESSOR.returnConnection(connection);

    }

    public void consumLogic(List<Industry> industrys) throws Exception {

        if (CollectionUtils.isEmpty(industrys)) {
            return;
        }

        Connection connection = DatabaseAccessor.Holder.ACCESSOR.getConnection();
        industrys.stream().forEach(industry -> {
            StringBuilder builder = new StringBuilder();
            builder.append("insert into industry ")
                    .append("(industry_name, industry_info,gmt_create,gmt_modified) ")
                    .append("values (?, ?,?,?)");
            String sql = builder.toString();
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, industry.getIndustryName());
                statement.setString(2, industry.getIndustryInfo());
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
        DatabaseAccessor.Holder.ACCESSOR.returnConnection(connection);

    }
}
