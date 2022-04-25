import {useEffect, useState} from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import {format} from 'date-fns'

export function Table() {
	const [pending, setPending] = useState([])

	useEffect(() => {
		axios.get('http://localhost:8080/holidaysystem/api/holiday-request/all').then((resp) => {
			const pending = resp.data.filter(s => s.status === 'PENDING');
			console.log(pending);
			setPending(pending)
		})
	}, [])

	function getEmpName(id) {
		axios.get(`http://localhost:8080/holidaysystem/api/employee/${id}`).then(data => {
			console.log(data);
		})
	}

	return (
		<div className='flex flex-col'>
			<h3 className='text-lg leading-6 font-bold text-gray-900'>
				Pending Requests
			</h3>
			<div className='py-5 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8'>
				<div className='py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8'>
					<div className='shadow overflow-hidden border-b border-gray-200 sm:rounded-lg'>
						<table className='min-w-full divide-y divide-gray-200'>
							<thead className='bg-gray-50'>
								<tr>
									<th
										scope='col'
										className='px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
										Employee
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

									<th scope='col' className='relative px-6 py-3'>
										<span className='sr-only'>Edit</span>
									</th>
								</tr>
							</thead>
							<tbody className='bg-white divide-y divide-gray-200'>
								{pending.map((p) => (
									<tr key={p.employeeId}>
										<td className='px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900'>
											{p.employeeId}
										</td>
										<td className='px-6 py-4 whitespace-nowrap text-sm text-gray-500'>
											{format(new Date(p.startDate), 'dd/MM/yyyy')}
										</td>
										<td className='px-6 py-4 whitespace-nowrap text-sm text-gray-500'>
											{format(new Date(p.endDate), 'dd/MM/yyyy')}
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
	);
}
