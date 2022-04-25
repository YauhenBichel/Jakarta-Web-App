import { useEffect, useState } from 'react';
import axios from 'axios';
import { ClockIcon, CheckIcon, BanIcon } from '@heroicons/react/outline';

export function StatCards() {
	const [requests, setRequests] = useState({
		pending: 0,
		approved: 0,
		rejected: 0,
	})

	useEffect(() => {
		axios.get('http://localhost:8080/holidaysystem/api/holiday-request/all').then((resp) => {
			const pending = resp.data.filter(s => s.status === 'PENDING').length;
			const approved = resp.data.filter(s => s.status === 'APPROVED').length;
			const rejected = resp.data.filter(s => s.status === 'REJECTED').length;
			setRequests({
				pending,
				approved,
				rejected
			})
		})
	}, [])

	return (
		<div>
			<h3 className='text-lg leading-6 font-bold text-gray-900'>Requests</h3>
			<dl className='mt-5 grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-3'>
				<div
					className='relative bg-white pt-5 px-4 sm:pt-6 sm:px-6 shadow rounded-lg overflow-hidden'>
					<dt>
						<div className='absolute bg-blue-500 rounded-md p-3'>
							<ClockIcon className='h-6 w-6 text-white' aria-hidden='true' />
						</div>
						<p className='ml-16 text-sm font-medium text-gray-500 truncate'>
							Pending Requests
						</p>
					</dt>
					<dd className='ml-16 pb-6 flex items-baseline sm:pb-7'>
						<p className='text-2xl font-semibold text-gray-900'>
							{requests.pending}
						</p>
					</dd>
				</div>
				<div
					className='relative bg-white pt-5 px-4 sm:pt-6 sm:px-6 shadow rounded-lg overflow-hidden'>
					<dt>
						<div className='absolute bg-blue-500 rounded-md p-3'>
							<CheckIcon className='h-6 w-6 text-white' aria-hidden='true' />
						</div>
						<p className='ml-16 text-sm font-medium text-gray-500 truncate'>
							Approved Requests
						</p>
					</dt>
					<dd className='ml-16 pb-6 flex items-baseline sm:pb-7'>
						<p className='text-2xl font-semibold text-gray-900'>
							{requests.approved}
						</p>
					</dd>
				</div>
				<div
					className='relative bg-white pt-5 px-4 sm:pt-6 sm:px-6 shadow rounded-lg overflow-hidden'>
					<dt>
						<div className='absolute bg-blue-500 rounded-md p-3'>
							<BanIcon className='h-6 w-6 text-white' aria-hidden='true' />
						</div>
						<p className='ml-16 text-sm font-medium text-gray-500 truncate'>
							Rejected Requests
						</p>
					</dt>
					<dd className='ml-16 pb-6 flex items-baseline sm:pb-7'>
						<p className='text-2xl font-semibold text-gray-900'>
							{requests.rejected}
						</p>
					</dd>
				</div>
			</dl>
		</div>
	);
}
