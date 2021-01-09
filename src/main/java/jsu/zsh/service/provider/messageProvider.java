package jsu.zsh.service.provider;
import java.util.List;
import java.util.Map;

public class messageProvider {
    public String insertForCrowd(Map map){
        List<Long> stuIds  = (List<Long>) map.get("param1");
        String msID = ((Long)map.get("param2")).toString();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO 面向人群表(学号,消息Id)");
        sb.append("VALUES ");
        String template = "(%s,%s)";
        for (Long i:stuIds) {
            sb.append(String.format(template,i,msID));
        }
        return sb.toString();
    }
}
