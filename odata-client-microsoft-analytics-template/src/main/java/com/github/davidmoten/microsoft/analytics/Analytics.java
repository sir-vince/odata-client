package com.github.davidmoten.microsoft.analytics;

import com.github.davidmoten.guavamini.Preconditions;
import com.github.davidmoten.msgraph.builder.MsGraphClientBuilder;
import com.github.davidmoten.odata.client.HasContext;

import default_.container.Container;

public final class Analytics {

	//
//	private static final String SERVICE_BASE_URL = "https://analytics.dev.azure.com/amsadevelopment/_odata/v3.0";

	private Analytics() {
		// prevent instantiation
	}

	public static <T extends HasContext> Builder<T> service(Class<T> serviceClass) {
		return new Builder<T>(serviceClass);
	}

	public static final class Builder<T extends HasContext> {

		private final Class<T> serviceCls;
		private String baseUrl;

		Builder(Class<T> serviceClass) {
			Preconditions.checkNotNull(serviceClass);
			this.serviceCls = serviceClass;
		}
		
		public Builder2<T> baseUrl(String baseUrl) {
			Preconditions.checkNotNull(baseUrl);
			this.baseUrl = baseUrl;
			return new Builder2<T>(this);
		}
		
	}
	
	public static final class Builder2<T extends HasContext> {

		private final Builder<T> b;

		public Builder2(Builder<T> b) {
			this.b = b;
		}
		
		public  MsGraphClientBuilder.Builder<T> tenantName(String tenantName) {
			return new MsGraphClientBuilder<T> //
			(b.baseUrl, b.serviceCls::newInstance).tenantName(tenantName);
		}
	}

	

}
