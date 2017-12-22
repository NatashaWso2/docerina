/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.docgen.docs;

import org.ballerinalang.docgen.docs.utils.BallerinaDocGenTestUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.ballerinalang.compiler.tree.BLangPackage;
import org.wso2.ballerinalang.compiler.tree.BLangService;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Tests for service doc generation.
 */
public class BallerinaServiceDocGenTest {

    private String sourceRoot;

    @BeforeClass()
    public void setup() {
        sourceRoot = BallerinaServiceDocGenTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                "balFiles/services";
    }

    @Test(description = "Test a Bal file with one Service")
    public void testABalWithOneService() {
        try {
            Map<String, BLangPackage> docsMap =
                    BallerinaDocGenerator.generatePackageDocsFromBallerina(sourceRoot, "helloWorldService.bal");
            Assert.assertNotNull(docsMap);
            Assert.assertEquals(docsMap.size(), 1);
            BallerinaDocGenTestUtils.printDocMap(docsMap);

            BLangPackage doc = docsMap.get(".");
            Collection<BLangService> services = doc.getServices();
            Assert.assertEquals(services.size(), 1);

            BLangService service = services.iterator().next();
            Assert.assertEquals(service.getResources().size(), 1);
            Assert.assertEquals(service.getAnnotationAttachments().size(), 1);
        } catch (IOException e) {
            Assert.fail();
        } finally {
            BallerinaDocGenTestUtils.cleanUp();
        }
    }

    @Test(description = "Test a Bal file with multiple Services")
    public void testABalWithMultipleServices() {
        try {
            Map<String, BLangPackage> docsMap =
                    BallerinaDocGenerator.generatePackageDocsFromBallerina(sourceRoot,
                                                                           "balWith2Services.bal");
            Assert.assertNotNull(docsMap);
            Assert.assertEquals(docsMap.size(), 1);
            BallerinaDocGenTestUtils.printDocMap(docsMap);

            BLangPackage doc = docsMap.get(".");
            Collection<BLangService> services = doc.getServices();
            Assert.assertEquals(services.size(), 2);

            Iterator<BLangService> iterator = services.iterator();
            BLangService service1 = iterator.next();
            Assert.assertEquals(service1.getResources().size(), 1);
            Assert.assertEquals(service1.getResources().get(0).getName().value, "sayHello");

            BLangService service2 = iterator.next();
            Assert.assertEquals(service2.getResources().size(), 2);
            Assert.assertEquals(service2.getResources().get(0).getName().value, "echoResource_1");
            Assert.assertEquals(service2.getResources().get(1).getName().value, "echoResource_2");
        } catch (IOException e) {
            Assert.fail();
        } finally {
            BallerinaDocGenTestUtils.cleanUp();
        }
    }
}
