/*
 *     Copyright 2022-2022 Yauhen Bichel yb3129h@gre.ac.uk OR bichel.eugen@gmail.com 
 *     Student Id 001185491
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.holidaysystem.comparator;

import java.util.Comparator;

import javax.enterprise.context.ApplicationScoped;

import com.google.common.collect.ComparisonChain;
import com.holidaysystem.model.HolidayRequestModel;

/**
 * Comparator for prioritization 
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
public class HolidayRequestModelComparator implements Comparator<HolidayRequestModel> {

	@Override
	public int compare(HolidayRequestModel m1, HolidayRequestModel m2) {
		return ComparisonChain.start()
		.compare(m1.getTakenDays(), m2.getTakenDays())
		.compare(m1.getRequestedDays(), m2.getRequestedDays())
		.result();
	
	}
	
}
