<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- /*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/ -->

<html>

<jsp:include page="header_frag.jsp"/>

	<h2>PhIS ID Generator Sevice Documentation</h2>
	<p>URL example http://[dev|beta].phenoimageshare.org/data/rest/idGen/getAnnotationId</p>
		
	<h3>getAnnotationId</h3>
	<p>The method returns a UUID. The method behing will be reimplemented to return shorter IDs but the method signature won't change.</p>

<jsp:include page="footer_frag.jsp"/>

</html>