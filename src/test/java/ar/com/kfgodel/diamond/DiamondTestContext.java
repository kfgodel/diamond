package ar.com.kfgodel.diamond;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.diamond.api.classes.ClassLineage;

import java.util.function.Supplier;

/**
 * This type defines common test context for diamond specs
 * Created by kfgodel on 17/09/14.
 */
public interface DiamondTestContext extends TestContext {

    ClassLineage lineage();
    void lineage(Supplier<ClassLineage> definition);

}
