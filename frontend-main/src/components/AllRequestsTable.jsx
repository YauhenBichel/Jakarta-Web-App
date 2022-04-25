import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { format } from 'date-fns'

import { ComponentsLayout } from './ComponentsLayout';

let doesShowBroken = false;
let doesShowPending = false;

export function AllRequestsTable() {
	const [data, setData] = useState([])
	const [originData, setOriginData] = useState([])

	useEffect(() => {
		axios.get('http://localhost:8080/holidaysystem/api/holiday-request/all')
		.then((response) => {
			setData(response.data);
			setOriginData(response.data);
		})
		.catch((error) => {
			console.log(error)
		})
	}, [])

	function showOnlyPending(e) {
		doesShowPending = !doesShowPending;
		console.log('doesShowPending');

		let filteredData = data;

		if(doesShowPending) {
			filteredData = data.filter(record => {
				console.log(record);
				return record.status == 'PENDING';
			});

			setData(filteredData);
			return filteredData;
		} else {
			setData(originData);
			return originData;
		}
	}
	function showOnlyBroken(e) {
		doesShowBroken = !doesShowBroken;
		console.log('showOnDuty');
		console.log('filterRecords');

		let filteredData = data;

		if(doesShowBroken) {
			filteredData = data.filter(record => {
				console.log(record);
				return record.status == 'BROKEN';
			});

			setData(filteredData);
			return filteredData;
		} else {
			setData(originData);
			return originData;
		}
	}

	return (
		<ComponentsLayout>
			<div className='flex flex-col'>
				<div className='flex items-center justify-between'>
					<h3 className='text-lg leading-6 font-bold text-gray-900'>
						All Requests
					</h3>
					<span className='relative z-0 inline-flex shadow-sm rounded-md'>

			<button
				type='button'
				onClick={showOnlyPending}
				className='-ml-px relative inline-flex items-center px-4 py-2 border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:outline-none focus:ring-1 focus:ring-blue-500 focus:border-blue-500'>
				PENDING
			</button>
			<button
				type='button'
				onClick={showOnlyBroken}
				className='-ml-px relative inline-flex items-center px-4 py-2 rounded-r-md border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:outline-none focus:ring-1 focus:ring-blue-500 focus:border-blue-500'>
				BROKEN
			</button>
		</span>
				</div>
				<div className='py-5 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8'>
					<div className='py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8'>
						<div className='shadow overflow-hidden border-b border-gray-200 sm:rounded-lg'>
							<table className='min-w-full divide-y divide-gray-200'>
								<thead className='bg-gray-50'>
									<tr>
										<th
											scope='col'
											className='px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
											Employee ID
										</th>
										<th
											scope='col'
											className='px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
											Start Date
										</th>
										<th
											scope='col'
											className='px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
											End Date
										</th>
										<th
											scope='col'
											className='px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
											Status
										</th>
										<th scope='col' className='relative px-6 py-3'>
											<span className='sr-only'>Edit</span>
										</th>
									</tr>
								</thead>
								<tbody className='bg-white divide-y divide-gray-200'>
									{data.map((request) => (
										<tr key={request.id}>
											<td className='px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900'>
												{request.employeeId}
											</td>
											<td className='px-6 py-4 whitespace-nowrap text-sm text-gray-500'>
												{format(new Date(request.startDate), 'dd/MM/yyyy')}
											</td>
											<td className='px-6 py-4 whitespace-nowrap text-sm text-gray-500'>
												{format(new Date(request.endDate), 'dd/MM/yyyy')}
											</td>
											<td className='px-6 py-4 whitespace-nowrap text-sm text-gray-500'>
												{request.status}
											</td>
											<td className='px-6 py-4 whitespace-nowrap text-right text-sm font-medium'>
												<Link
													to='/request-example'
													className='text-blue-600 hover:text-blue-900'>
													More Details
												</Link>
											</td>
										</tr>
									))}
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</ComponentsLayout>
	);
}
