/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdno.servicechain.mocoserver;

import org.codehaus.jackson.type.TypeReference;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.servicechain.ServiceChainPath;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.testframework.http.model.HttpRequest;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

/**
 * SbiAdapterSuccessServer test class.<br>
 * 
 * @author
 * @version SDNO 0.5 August 22, 2016
 */
public class SbiAdapterSuccessServer extends MocoHttpServer {

    private static final String CREATE_SERVICECHAIN_SUCCESS_SBI_FILE =
            "src/integration-test/resources/sbiadapter/createservicechainsuccess.json";

    private static final String DELETE_SERVICECHAINC_SUCCESS_SBI_FILE =
            "src/integration-test/resources/sbiadapter/deleteservicechainsuccess.json";

    public SbiAdapterSuccessServer() {

    }

    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair(CREATE_SERVICECHAIN_SUCCESS_SBI_FILE,
                new CreateServiceChainSuccessSbiResponseHandler());

        this.addRequestResponsePair(DELETE_SERVICECHAINC_SUCCESS_SBI_FILE);

    }

    private class CreateServiceChainSuccessSbiResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            HttpRequest httpRequest = httpObject.getRequest();
            HttpResponse httpResponse = httpObject.getResponse();
            ServiceChainPath sfp = JsonUtil.fromJson(httpRequest.getData(), new TypeReference<ServiceChainPath>() {});

            ResultRsp<ServiceChainPath> newResult = new ResultRsp<ServiceChainPath>(ErrorCode.OVERLAYVPN_SUCCESS);

            newResult.setData(sfp);
            httpResponse.setData(JsonUtil.toJson(newResult));
        }
    }
}
