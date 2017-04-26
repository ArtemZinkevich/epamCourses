import com.rmznk.xmltask.dom.FlowerDOMBuilder;
import com.rmznk.xmltask.sax.FlowersSAXBuilder;
import com.rmznk.xmltask.stax.FlowerStAXBuilder;

/**
 * Created by RTM on 23.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        FlowersSAXBuilder builder = new FlowersSAXBuilder();
        builder.buildListFlowers("data/plants.xml");
        System.out.println(builder.getFlowers());
        FlowerStAXBuilder stax = new FlowerStAXBuilder();
        stax.buildListFlowers("data/plants.xml");
        System.out.println(stax.getFlowers());
        FlowerDOMBuilder domB=new FlowerDOMBuilder();
        domB.buildListFlowers("data/plants.xml");
        System.out.println(domB.getFlowers());
    }
}
