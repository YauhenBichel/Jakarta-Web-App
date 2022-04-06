import { useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

import { ComponentsLayout } from './ComponentsLayout';
import { useState } from 'react/cjs/react.development';

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

export function AllEmployees() {
	const [data, setData] = useState([])
	useEffect(() => {
		axios.get('http://localhost:8080/holidaysystem/api/employee/all')
			.then((response) => {
				console.log(response.data)
				setData(response.data)
			})
			.catch((error) => {
				console.log(error)
			})
	}, [])

	return (
		<ComponentsLayout>
			<div className='flex items-center justify-between'>
				<h3 className='text-lg leading-6 font-bold text-gray-900'>
					All Employees
				</h3>
				<Link
					to='/add-employee'
					className='relative z-0 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-blue-600 shadow-sm hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500'>
					Add Employees
				</Link>
			</div>
			{data.length === 0 ? (
				<div className='py-12 sm:py-32 text-center'>
					<svg
						className='mx-auto h-12 w-12 text-gray-400'
						fill='none'
						viewBox='0 0 24 24'
						stroke='currentColor'
						aria-hidden='true'>
						<path
							vectorEffect='non-scaling-stroke'
							strokeLinecap='round'
							strokeLinejoin='round'
							strokeWidth={2}
							d='M9 13h6m-3-3v6m-9 1V7a2 2 0 012-2h6l2 2h6a2 2 0 012 2v8a2 2 0 01-2 2H5a2 2 0 01-2-2z'
						/>
					</svg>
					<h3 className='mt-2 text-sm font-medium text-gray-900'>
						No Employees yet
					</h3>
					<p className='mt-1 text-sm text-gray-500'>
						Get started by creating adding people that work with you.
					</p>
					<div className='mt-6'>
						<button
							type='button'
							className='inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500'>
							Add Employees
						</button>
					</div>
				</div>
			) : (
				<ul role="list" className="py-4 grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
					{data.map((employee, idx) => (
						<li key={idx} className="col-span-1 bg-white rounded-lg shadow divide-y divide-gray-200">
							<div className="w-full flex items-center justify-between p-6 space-x-6">
								<div className="flex-1 truncate">
									<div className="flex items-center space-x-3">
										<h3 className="text-gray-900 text-sm font-medium truncate">{`${employee.firstName} ${employee.lastName}`}</h3>
										<span className="flex-shrink-0 inline-block px-2 py-0.5 text-green-800 text-xs font-medium bg-green-100 rounded-full">
											{employee.role}
										</span>
									</div>
									<p className="mt-1 text-gray-500 text-sm truncate">{employee.department}</p>
								</div>
							</div>
						</li>
					))}
    		</ul>
			)}
		</ComponentsLayout>
	);
}
