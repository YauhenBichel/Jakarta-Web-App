import { ClockIcon, CheckIcon, BanIcon } from '@heroicons/react/outline';

const stats = [
	{
		id: 1,
		name: 'Pending Requests',
		stat: '23',
		icon: ClockIcon,
	},
	{
		id: 2,
		name: 'Approved Requests',
		stat: '13',
		icon: CheckIcon,
	},
	{
		id: 3,
		name: 'Rejected Requests',
		stat: '10',
		icon: BanIcon,
	},
];

export function StatCards() {
	return (
		<div>
			<h3 className='text-lg leading-6 font-bold text-gray-900'>Requests</h3>
			<dl className='mt-5 grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-3'>
				{stats.map((item) => (
					<div
						key={item.id}
						className='relative bg-white pt-5 px-4 sm:pt-6 sm:px-6 shadow rounded-lg overflow-hidden'>
						<dt>
							<div className='absolute bg-blue-500 rounded-md p-3'>
								<item.icon className='h-6 w-6 text-white' aria-hidden='true' />
							</div>
							<p className='ml-16 text-sm font-medium text-gray-500 truncate'>
								{item.name}
							</p>
						</dt>
						<dd className='ml-16 pb-6 flex items-baseline sm:pb-7'>
							<p className='text-2xl font-semibold text-gray-900'>
								{item.stat}
							</p>
						</dd>
					</div>
				))}
			</dl>
		</div>
	);
}
