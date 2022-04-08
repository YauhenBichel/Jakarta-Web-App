import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { format } from 'date-fns'

import { ComponentsLayout } from './ComponentsLayout';
import { FilterButton } from './FilterButton';

// const people = [
// 	{
// 		name: 'Jane Cooper',
// 		title: 'Regional Paradigm Technician',
// 		department: 'Optimization',
// 		role: 'Admin',
// 		email: 'jane.cooper@example.com',
// 		priority: 1,
// 		image:
// 			'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=4&w=256&h=256&q=60',
// 	},
// 	{
// 		name: 'Jane Cooper',
// 		title: 'Regional Paradigm Technician',
// 		department: 'Optimization',
// 		role: 'Admin',
// 		email: 'jane.cooper@example.com',
// 		priority: 1,
// 		image:
// 			'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=4&w=256&h=256&q=60',
// 	},
// 	{
// 		name: 'Jane Cooper',
// 		title: 'Regional Paradigm Technician',
// 		department: 'Optimization',
// 		role: 'Admin',
// 		email: 'jane.cooper@example.com',
// 		priority: 1,
// 		image:
// 			'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=4&w=256&h=256&q=60',
// 	},
// 	{
// 		name: 'Jane Cooper',
// 		title: 'Regional Paradigm Technician',
// 		department: 'Optimization',
// 		role: 'Admin',
// 		email: 'jane.cooper@example.com',
// 		priority: 1,
// 		image:
// 			'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=4&w=256&h=256&q=60',
// 	},
// 	{
// 		name: 'Jane Cooper',
// 		title: 'Regional Paradigm Technician',
// 		department: 'Optimization',
// 		role: 'Admin',
// 		email: 'jane.cooper@example.com',
// 		priority: 1,
// 		image:
// 			'https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=4&w=256&h=256&q=60',
// 	},
// ];

export function AllRequestsTable() {
	const [data, setData] = useState([])

	useEffect(() => {
		axios.get('http://localhost:8080/holidaysystem/api/holiday-request/all')
		.then((response) => {
			console.log(response.data);
			setData(response.data)
		})
		.catch((error) => {
			console.log(error)
		})
	}, [])

	return (
		<ComponentsLayout>
			<div className='flex flex-col'>
				<div className='flex items-center justify-between'>
					<h3 className='text-lg leading-6 font-bold text-gray-900'>
						All Requests
					</h3>
					<FilterButton />
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
										<th
											scope='col'
											className='px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
											Priority
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
											<td className='px-6 py-4 whitespace-nowrap text-sm text-gray-500'>
												{/* {person.priority} */}
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
