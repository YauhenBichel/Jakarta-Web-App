import { useState } from 'react';
import { DateRangePicker } from 'react-date-range';
import { addDays } from 'date-fns';
import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';

import { ComponentsLayout } from './ComponentsLayout';

export function NewRequest() {
	const [state, setState] = useState([
		{
			startDate: new Date(),
			endDate: addDays(new Date(), 7),
			key: 'selection',
		},
	]);

	return (
		<ComponentsLayout>
			<h3 className='text-lg leading-6 font-bold text-gray-900'>New Request</h3>
			<div className='py-5'>
				<DateRangePicker
					className='w-full'
					onChange={(item) => setState([item.selection])}
					showSelectionPreview={true}
					moveRangeOnFirstSelection={false}
					months={3}
					ranges={state}
					direction='horizontal'
				/>
			</div>
			<div className='flex justify-end'>
				<button
					type='button'
					className='inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500'>
					Submit Request
				</button>
			</div>
		</ComponentsLayout>
	);
}
